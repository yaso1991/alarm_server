/**
 * projectName: alarm_server
 * fileName: AlarmInfoService.java
 * packageName: club.yaso91.alarm_server.service
 * date: 2019-10-05 19:02
 * copyright(c) 2017-2020 FuYun design studio.
 */
package club.yaso91.alarm_server.service;

import club.yaso91.alarm_server.entity.AlarmInfo;
import club.yaso91.alarm_server.entity.CardReader;
import club.yaso91.alarm_server.mapper.AlarmInfoMapper;
import club.yaso91.alarm_server.mapper.CardReaderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @version: V1.0
 * @author: Yaso
 * @className: AlarmInfoService
 * @packageName: club.yaso91.alarm_server.service
 * @description:
 * @data: 2019-10-05 19:02
 **/
@Service
public class AlarmInfoService {
    @Autowired
    private AlarmInfoMapper alarmInfoMapper;
    @Autowired
    private CardReaderMapper cardReaderMapper;

    public ArrayList<AlarmInfo> getAlarmInfos() {
        return alarmInfoMapper.selectAlarmInfos();
    }

    public boolean addAlarmInfo(AlarmInfo alarmInfo) {
        return alarmInfoMapper.insertAlarmInfo(alarmInfo) == 1;
    }

    public ArrayList<CardReader> loadCardReadersData() {
        return cardReaderMapper.selectAll();
    }

    public boolean fixAlarmInfo(AlarmInfo alarmInfo) {
        return alarmInfoMapper.updateAlarmInfo(alarmInfo) == 1;
    }

    public boolean deleteAlarmInfos(ArrayList<Integer> ids) {
        return alarmInfoMapper.deleteAlarmInfos(ids) != 0;
    }
}
