/**
 * projectName: alarm_server
 * fileName: AlarmGatheringService.java
 * packageName: club.yaso91.alarm_server.service
 * date: 2019-10-22 19:21
 * copyright(c) 2017-2020 FuYun design studio.
 */
package club.yaso91.alarm_server.service;

import club.yaso91.alarm_server.common.YasoUtils;
import club.yaso91.alarm_server.component.EmailSender;
import club.yaso91.alarm_server.component.ModbusCom;
import club.yaso91.alarm_server.component.ModbusManger;
import club.yaso91.alarm_server.component.ModbusPoint;
import club.yaso91.alarm_server.entity.AlarmInfo;
import club.yaso91.alarm_server.entity.AlarmItemInfo;
import club.yaso91.alarm_server.mapper.AlarmInfoMapper;
import club.yaso91.alarm_server.mapper.AlarmItemInfoMapper;
import club.yaso91.alarm_server.mapper.EmployeeInfoMapper;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * @version: V1.0
 * @author: Yaso
 * @className: AlarmGatheringService
 * @packageName: club.yaso91.alarm_server.service
 * @description:
 * @data: 2019-10-22 19:21
 **/
@Service
public class AlarmStateService {
    private ModbusManger modbusManger = new ModbusManger();

    @Autowired
    private AlarmInfoMapper alarmInfoMapper;
    @Autowired
    private AlarmItemInfoMapper alarmItemInfoMapper;
    @Autowired
    private EmailSender emailSender;
    @Autowired
    private EmployeeInfoMapper employeeInfoMapper;

    public AlarmStateService() {
        boot();
    }

    private void boot() {
        modbusManger.startCommunication();
    }

    public void updateAlarmInfo() {
        ArrayList<ModbusCom> coms = modbusManger.getComs();
        for (ModbusCom com : coms) {
            HashMap<String, ModbusPoint> points = com.getPoints();
            for (String key : points.keySet()) {
                ModbusPoint point = points.get(key);
                String current = point.getValue();

                //检测为正常
                if (current.equals("false")) {
                    alarmInfoMapper.updateStateIsNormal(com.getPortName(), point.getName());
                    continue;
                }
                //检测到故障
                if (current.equals("true")) {
                    AlarmInfo alarmInfo = alarmInfoMapper.find(com.getPortName(), point.getName());
                    AlarmItemInfo alarmItemInfo = new AlarmItemInfo();
                    //刚发生报警
                    if (alarmInfo.getAlarmTime() == null) {
                        alarmInfo.setAlarmTime(new Timestamp(System.currentTimeMillis()));
                        alarmInfo.setAlarming(true);
                        alarmInfoMapper.updateStateAlarmed(alarmInfo);
                        setItemInfo(alarmInfo, alarmItemInfo);
                        alarmItemInfoMapper.insert(alarmItemInfo);
                        continue;
                    }


                    //持续发生报警
                    //报警推送
                    boolean needPushing = false;
                    if (alarmInfo.getAlarmSpan() >= 20 && alarmInfo.getPushLevel().equals("未推送")) {
                        alarmInfo.setPushLevel("班组长级");
                        needPushing = true;
                    } else if (alarmInfo.getAlarmSpan() >= 40 && alarmInfo.getPushLevel().equals("班组长级")) {
                        alarmInfo.setPushLevel("主任级");
                        needPushing = true;

                    } else if (alarmInfo.getAlarmSpan() >= 60 && alarmInfo.getPushLevel().equals("主任级")) {
                        alarmInfo.setPushLevel("经理级");
                        needPushing = true;
                    }

                    //需要推送
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
                                emailSender.sendPushMail(from, to, subject, context,
                                        emails.toArray(new String[emails.size()]));
                            } else {
                                emailSender.sendPushMail(from, to, subject, context);
                            }
                        }
                    }

                    alarmInfoMapper.updateAlarming(alarmInfo);
                    setItemInfo(alarmInfo, alarmItemInfo);
                    alarmItemInfoMapper.update(alarmItemInfo);
                    continue;
                }


            }
        }
    }

    /**
     * 汇总推送
     */
    public void pushSumInfo() {
        //查询昨日报警记录
        ArrayList<AlarmItemInfo> alarmItemInfos =
                alarmItemInfoMapper.selectSumInfos(new Timestamp(YasoUtils.getYestodayMills()),
                        new Timestamp(YasoUtils.getYestodayMills() + 86399999), null, null);

        //生成EXCEL报表
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        HSSFRow headRow = sheet.createRow(0);
        headRow.createCell(0).setCellValue("序号");
        headRow.createCell(1).setCellValue("ID");
        headRow.createCell(2).setCellValue("报警点");
        headRow.createCell(3).setCellValue("报警时间");
        headRow.createCell(4).setCellValue("报警时长");
        headRow.createCell(5).setCellValue("结束时间");
        headRow.createCell(6).setCellValue("员工编号");
        headRow.createCell(7).setCellValue("员工姓名");
        headRow.createCell(8).setCellValue("推送");
        headRow.createCell(9).setCellValue("组长编号");
        headRow.createCell(10).setCellValue("组长姓名");

        int index = 1;
        for (AlarmItemInfo itemInfo : alarmItemInfos) {
            HSSFRow headRowTemp = sheet.createRow(index);
            headRowTemp.createCell(0).setCellValue(index);
            headRowTemp.createCell(1).setCellValue(itemInfo.getId());
            headRowTemp.createCell(2).setCellValue(itemInfo.getAlarmInfo().getName());
            headRowTemp.createCell(3).setCellValue(itemInfo.getAlarmStartTime().toString());
            headRowTemp.createCell(4).setCellValue(itemInfo.getAlarmSpan());
            headRowTemp.createCell(5).setCellValue(new Timestamp(itemInfo.getAlarmStartTime().getTime() + itemInfo.getAlarmSpan()*1000).toString());
            headRowTemp.createCell(6).setCellValue(itemInfo.getEmployee().getWorkId());
            headRowTemp.createCell(7).setCellValue(itemInfo.getEmployee().getName());
            headRowTemp.createCell(8).setCellValue(itemInfo.getPushLevel());
            headRowTemp.createCell(9).setCellValue(itemInfo.getMaster().getWorkId());
            headRowTemp.createCell(10).setCellValue(itemInfo.getMaster().getName());
            index++;
        }
        workbook.setActiveSheet(0);


        String dir = "sumInfos/";
        try {
            File fileDir = new File(dir);
            if (!fileDir.exists()) {
                fileDir.mkdir();
            }
            String fileName =
                    "/报警汇总" + new SimpleDateFormat("yyyy_MM_dd").format(new Date(YasoUtils.getYestodayMills())) +
                            ".xls";
            File file =
                    new File(fileDir, fileName);
            if (file.exists()) {
                file.delete();
                file.createNewFile();
            }
            FileOutputStream outputStream = new FileOutputStream(file);
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //EXCEL报表保存到本地

        //发送本地报表到符合条件的emails.

    }

    private void setItemInfo(AlarmInfo alarmInfo, AlarmItemInfo alarmItemInfo) {
        alarmItemInfo.setAlarmInfo(alarmInfo);
        alarmItemInfo.setAlarmSpan(alarmInfo.getAlarmSpan());
        alarmItemInfo.setAlarmStartTime(alarmInfo.getAlarmTime());
        alarmItemInfo.setEmployee(alarmInfo.getEmployee());
        alarmItemInfo.setMaster(alarmInfo.getMaster());
        alarmItemInfo.setPushLevel(alarmInfo.getPushLevel());
    }
}

