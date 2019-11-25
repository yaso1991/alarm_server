/*
 * Copyright(c)2002-2019, 雅俗工作室.
 *    项目名称:alarm_server
 *    文件名称:AlarmInfoController.java
 *    Date:2019/11/25 上午9:32
 *    Author:Yaso
 */
package club.yaso91.alarmserver.controller;

import club.yaso91.alarmserver.domain.AlarmInfo;
import club.yaso91.alarmserver.domain.CardReader;
import club.yaso91.alarmserver.service.AlarmInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @version: V1.0
 * @author: Yaso
 * @className: AlarmInfoController
 * @packageName: club.yaso91.alarmserver.controller
 * @description:
 * @data: 2019-10-05 16:19
 **/
@RestController
@RequestMapping("/alarmInfo")
public class AlarmInfoController {
    @Autowired
    private AlarmInfoService alarmInfoService;

    @RequestMapping
    public List<AlarmInfo> getAlarmInfos() {
        return alarmInfoService.getAlarmInfos();
    }
    @RequestMapping(value = "/addAlarmInfo",method = RequestMethod.POST)
    public boolean addAlarmInfo(@RequestBody AlarmInfo alarmInfo) {
        return alarmInfoService.insertAlarmInfo(alarmInfo);
    }

    @RequestMapping("/loadCardReadersData")
    public List<CardReader> loadCardReadersData() {
        return alarmInfoService.loadCardReadersData();
    }

    @RequestMapping(value="/fixAlarmInfo",method = RequestMethod.POST)
    public boolean fixAlarmInfo(@RequestBody AlarmInfo alarmInfo) {
        return alarmInfoService.updateAlarmInfo(alarmInfo);
    }

    @RequestMapping(value = "/deleteAlarmInfos",method = RequestMethod.POST)
    public boolean deleteAlarmInfos(@RequestBody ArrayList<Integer> ids) {
        return alarmInfoService.deleteAlarmInfos(ids);
    }
}

