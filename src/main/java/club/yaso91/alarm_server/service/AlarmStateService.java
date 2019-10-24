/**
 * projectName: alarm_server
 * fileName: AlarmGatheringService.java
 * packageName: club.yaso91.alarm_server.service
 * date: 2019-10-22 19:21
 * copyright(c) 2017-2020 FuYun design studio.
 */
package club.yaso91.alarm_server.service;

import club.yaso91.alarm_server.component.EmailSender;
import club.yaso91.alarm_server.component.ModbusCom;
import club.yaso91.alarm_server.component.ModbusManger;
import club.yaso91.alarm_server.component.ModbusPoint;
import club.yaso91.alarm_server.entity.AlarmInfo;
import club.yaso91.alarm_server.entity.AlarmItemInfo;
import club.yaso91.alarm_server.mapper.AlarmInfoMapper;
import club.yaso91.alarm_server.mapper.AlarmItemInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
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

    public AlarmStateService() {
        boot();
    }

    private void boot() {
        modbusManger.startCommunication();
    }

    //    @Scheduled(fixedDelay = 1000)
    public void updateAlarmInfo() {
        ArrayList<ModbusCom> coms = modbusManger.getComs();
        for (ModbusCom com : coms) {
            HashMap<String, ModbusPoint> points = com.getPoints();
            for (String key : points.keySet()) {
                ModbusPoint point = points.get(key);
                String current = point.getValue();

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
                    if (alarmInfo.getAlarmSpan() >= 20 && alarmInfo.getPushLevel().equals("未推送")) {
                        alarmInfo.setPushLevel("班组长级");
                        emailSender.sendPushMail("1441825297@qq.com", "1721662545@qq.com",
                                "发生班组长级报警推送",
                                new Timestamp(System.currentTimeMillis()).toString()
                                        + ":" + point.getName() + "发生班组长级推送.");
                    } else if (alarmInfo.getAlarmSpan() >= 40 && alarmInfo.getPushLevel().equals("班组长级")) {
                        alarmInfo.setPushLevel("主任级");
                        emailSender.sendPushMail("1441825297@qq.com", "1721662545@qq.com",
                                "发生主任级报警推送",
                                new Timestamp(System.currentTimeMillis()).toString()
                                        + ":" + point.getName() + "发生主任级推送.");

                    } else if (alarmInfo.getAlarmSpan() >= 60 && alarmInfo.getPushLevel().equals("主任级")) {
                        alarmInfo.setPushLevel("经理级");
                        emailSender.sendPushMail("1441825297@qq.com", "1721662545@qq.com",
                                "发生班经理级报警推送",
                                new Timestamp(System.currentTimeMillis()).toString()
                                        + ":" + point.getName() + "发生经理级推送.");
                    } else {

                    }

                    alarmInfoMapper.updateAlarming(alarmInfo);
                    setItemInfo(alarmInfo, alarmItemInfo);
                    alarmItemInfoMapper.update(alarmItemInfo);
                    continue;
                }

                //检测为正常
                if (current.equals("false")) {
                    alarmInfoMapper.updateStateIsNormal(com.getPortName(), point.getName());

                }

            }
        }
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

