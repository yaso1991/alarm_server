/**
 * projectName: alarmserver
 * fileName: AlarmGatheringService.java
 * packageName: club.yaso91.alarmserver.service
 * date: 2019-10-22 19:21
 * copyright(c) 2017-2020 FuYun design studio.
 */
package club.yaso91.alarmserver.service;

import club.yaso91.alarmserver.common.AlarmItemInfoExcelHandler;
import club.yaso91.alarmserver.component.EmailSender;
import club.yaso91.alarmserver.component.ModbusCom;
import club.yaso91.alarmserver.component.ModbusManger;
import club.yaso91.alarmserver.component.ModbusPoint;
import club.yaso91.alarmserver.entity.AlarmInfo;
import club.yaso91.alarmserver.entity.AlarmItemInfo;
import club.yaso91.alarmserver.entity.SystemConfig;
import club.yaso91.alarmserver.mapper.AlarmInfoMapper;
import club.yaso91.alarmserver.mapper.AlarmItemInfoMapper;
import club.yaso91.alarmserver.mapper.EmployeeInfoMapper;
import club.yaso91.client.util.YasoUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * @version: V1.0
 * @author: Yaso
 * @className: AlarmGatheringService
 * @packageName: club.yaso91.alarmserver.service
 * @description:
 * @data: 2019-10-22 19:21
 **/
@Service
@Slf4j
public class AlarmStateService {
    private final String SUM_INFO_DIR = "../sumInfos/";
    private ModbusManger modbusManger = new ModbusManger();

    @Autowired
    private AlarmInfoMapper alarmInfoMapper;
    @Autowired
    private AlarmItemInfoMapper alarmItemInfoMapper;
    @Autowired
    private EmailSender emailSender;
    @Autowired
    private EmployeeInfoMapper employeeInfoMapper;
    @Autowired
    private LocalDataService localDataService;

    public AlarmStateService() {
        init();
    }

    private void init() {
        // 开始读取串口数据.
        modbusManger.startCommunication();
    }

    /**
     * 定时从ModbusManager里读取报警信息.
     */
    @Scheduled(initialDelay = 10000, fixedDelay = 1000)
    public void updateAlarmInfo() {
        // 获取串口
        ArrayList<ModbusCom> coms = modbusManger.getComs();
        for (ModbusCom com : coms) {
            if (!com.isConnected()) {
                continue;
            }

            HashMap<String, ModbusPoint> points = com.getPoints();
            for (String key : points.keySet()) {
                ModbusPoint point = points.get(key);
                String current = point.getValue();

                //  检测为正常
                if ("false".equals(current)) {
                    alarmInfoMapper.updateStateIsNormal(com.getPortName(), point.getName());
                    continue;
                }
                // 检测到故障
                if ("true".equals(current)) {
                    AlarmInfo alarmInfo = alarmInfoMapper.find(com.getPortName(), point.getName());
                    AlarmItemInfo alarmItemInfo = new AlarmItemInfo();
                    // 刚发生报警
                    if (alarmInfo.getAlarmTime() == null) {
                        alarmInfo.setAlarmTime(new Timestamp(System.currentTimeMillis()));
                        alarmInfo.setAlarming(true);
                        alarmInfoMapper.updateStateAlarmed(alarmInfo);
                        setItemInfo(alarmInfo, alarmItemInfo);
                        alarmItemInfoMapper.insert(alarmItemInfo);
                        continue;
                    }

                    // 持续发生报警
                    boolean needPushing = false;
                    SystemConfig systemConfig = localDataService.getLocalSystemConfig();
                    if (alarmInfo.getAlarmSpan() >= systemConfig.getMonitorPushDelay() && "未推送".equals(alarmInfo.getPushLevel())) {
                        alarmInfo.setPushLevel("班组长级");
                        needPushing = true;
                    } else if (alarmInfo.getAlarmSpan() >= systemConfig.getMasterPushDelay() && "班组长级".equals(alarmInfo.getPushLevel())) {
                        alarmInfo.setPushLevel("主任级");
                        needPushing = true;

                    } else if (alarmInfo.getAlarmSpan() >= systemConfig.getManagerPushDelay() && "主任级".equals(alarmInfo.getPushLevel())) {
                        alarmInfo.setPushLevel("经理级");
                        needPushing = true;
                    }

                    // 需要推送
                    if (needPushing) {
                        ArrayList<String> emails = employeeInfoMapper.selectEmails(alarmInfo.getPushLevel().replace("级",
                                ""));
                        if (!emails.isEmpty()) {
                            String from = "1441825297@qq.com";
                            String to = emails.get(0);
                            String subject =
                                    new Timestamp(System.currentTimeMillis()).toString() + ": " + point.getName() +
                                            " 发生 "
                                            + alarmInfo.getPushLevel() + " 报警推送.";
                            String context =
                                    new Timestamp(System.currentTimeMillis()).toString() + ": " + point.getName() +
                                            " 发生 "
                                            + alarmInfo.getPushLevel() + " 报警推送,"
                                            + "报警发生时间:" + alarmInfo.getAlarmTime()
                                            + ",报警时长超过 " + alarmInfo.getAlarmSpan() + " 秒未有人处理,"
                                            + "当班员工:" + alarmInfo.getEmployee().getName() + ",工号:" + alarmInfo.getEmployee().getWorkId();
                            if (emails.size() > 1) {
                                emails.remove(0);
                                emailSender.sendAlarmMail(from, to, subject, context,
                                        emails.toArray(new String[emails.size()]));
                            } else {
                                emailSender.sendAlarmMail(from, to, subject, context);
                            }
                        }
                    }

                    // 存入数据库
                    alarmInfoMapper.updateAlarming(alarmInfo);
                    setItemInfo(alarmInfo, alarmItemInfo);
                    alarmItemInfoMapper.update(alarmItemInfo);
                    continue;
                }


            }
        }
    }

    /**
     * 定时汇总推送
     */
    @Scheduled(cron = "0/50 * * * * ?")
    public void checkAndPushSumInfo() {
        // 如果时间点和设置的时间点相吻合,推送汇总信息
        Calendar now = Calendar.getInstance();
        SystemConfig localSystemConfig = localDataService.getLocalSystemConfig();
        Calendar sumPushTime = Calendar.getInstance();
        sumPushTime.setTime(localSystemConfig.getSumPushTime());
        if (sumPushTime.get(Calendar.HOUR_OF_DAY) == now.get(Calendar.HOUR_OF_DAY) && sumPushTime.get(Calendar.MINUTE) == now.get(Calendar.MINUTE)) {
            pushSumInfo();
        }
    }

    /**
     * 推送汇总信息逻辑.
     */
    private void pushSumInfo() {
        // 查询昨日报警记录
        long yestodayMills = YasoUtils.getYestodayMills() * 1000;
        Timestamp beginTime = new Timestamp(yestodayMills);
        Timestamp endTime = new Timestamp(yestodayMills + 86399999);
        ArrayList<AlarmItemInfo> alarmItemInfos =
                alarmItemInfoMapper.selectSumInfos(beginTime,
                        endTime, null, null);

        // 生成EXCEL报表
        HSSFWorkbook workbook = new HSSFWorkbook();
        AlarmItemInfoExcelHandler.generatorExcelBook(alarmItemInfos, workbook);

        // EXCEL报表保存到本地
        String fileName =
                "/报警汇总" + new SimpleDateFormat("yyyy_MM_dd").format(new Date(yestodayMills)) +
                        ".xls";
        File file = null;
        File fileDir = new File(SUM_INFO_DIR);
        if (!fileDir.exists()) {
            fileDir.mkdir();
        }
        file = new File(fileDir, fileName);
        if (file.exists()) {
            file.delete();
            try {
                file.createNewFile();
            } catch (IOException e) {
                log.warn(e.toString());
            }
        }
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            workbook.write(outputStream);
        } catch (FileNotFoundException e) {
            log.warn(e.toString());
        } catch (IOException e) {
            log.warn(e.toString());
        } finally {
            //关闭流.
            if (null != outputStream) {
                try {
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        // 发送本地报表到符合条件的emails.
        ArrayList<String> emails = employeeInfoMapper.selectEmails("经理");
        if (emails == null || emails.size() < 1) {
            return;
        }
        if (emails.size() == 1) {
            emailSender.sendSumInfoMail(file, "1441825297@qq.com", emails.get(0), fileName.replace(".xls",
                    ""),
                    beginTime.toString() + "报警汇总,详见电子邮件的附件.");
            return;
        }
        if (emails.size() > 1) {
            String to = emails.get(0);
            emails.remove(0);
            emailSender.sendSumInfoMail(file, "1441825297@qq.com", to,
                    beginTime.toString() + "报警汇总",
                    beginTime.toString() + "报警汇总,详见电子邮件的附件.",
                    emails.toArray(new String[emails.size()]));
        }
    }

    /**
     * 根据发生报警的报警点信息生成报警条目信息.
     *
     * @param alarmInfo     发生报警的报警点
     * @param alarmItemInfo 需要添加或更新的报警条目信息.
     */
    private void setItemInfo(AlarmInfo alarmInfo, AlarmItemInfo alarmItemInfo) {
        alarmItemInfo.setAlarmInfo(alarmInfo);
        alarmItemInfo.setAlarmSpan(alarmInfo.getAlarmSpan());
        alarmItemInfo.setAlarmStartTime(alarmInfo.getAlarmTime());
        alarmItemInfo.setEmployee(alarmInfo.getEmployee());
        alarmItemInfo.setMaster(alarmInfo.getMaster());
        alarmItemInfo.setPushLevel(alarmInfo.getPushLevel());
    }
}

