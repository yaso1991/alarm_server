/**
 * projectName: alarm_server
 * fileName: AlarmGatheringService.java
 * packageName: club.yaso91.alarm_server.service
 * date: 2019-10-22 19:21
 * copyright(c) 2017-2020 FuYun design studio.
 */
package club.yaso91.alarm_server.service;

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
                System.out.println(point.getName() + ":" + current);

                //检测到故障
                if (current == "true") {
                    AlarmInfo alarmInfo = alarmInfoMapper.find(com.getPortName(), point.getName());
                    AlarmItemInfo alarmItemInfo = new AlarmItemInfo();
                    //刚发生报警
                    if (alarmInfo.getAlarmTime() == null) {
                        alarmInfo.setAlarmTime(new Timestamp(System.currentTimeMillis()));
                        alarmInfoMapper.updateStateAlarmed(alarmInfo);
                        setItemInfo(alarmInfo, alarmItemInfo);
                        alarmItemInfoMapper.insert(alarmItemInfo);
                        continue;
                    }

                    //持续发生报警
                    alarmInfoMapper.updateStateIsErring(alarmInfo);
                    setItemInfo(alarmInfo, alarmItemInfo);
                    alarmItemInfoMapper.update(alarmItemInfo);
                    continue;
                }

                //检测为正常
                if (current == "false") {
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

