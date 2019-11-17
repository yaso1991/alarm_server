/*
 * Copyright(c)2002-2019, 雅俗工作室.
 *    项目名称:alarm_server
 *    文件名称:AlarmInfoService.java
 *    Date:2019/11/17 上午11:13
 *    Author:Yaso
 */
package club.yaso91.alarmserver.service;

import club.yaso91.alarmserver.domain.AlarmInfo;
import club.yaso91.alarmserver.domain.CardReader;
import club.yaso91.alarmserver.mapper.AlarmInfoMapper;
import club.yaso91.alarmserver.mapper.CardReaderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @version: V1.0
 * @author: Yaso
 * @className: AlarmInfoService
 * @packageName: club.yaso91.alarmserver.service
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
