/**
 * projectName: alarm_server
 * fileName: AlarmInfoController.java
 * packageName: club.yaso91.alarm_server.controller
 * date: 2019-10-05 16:19
 * copyright(c) 2017-2020 FuYun design studio.
 */
package club.yaso91.alarm_server.controller;

import club.yaso91.alarm_server.entity.AlarmInfo;
import club.yaso91.alarm_server.entity.CardReader;
import club.yaso91.alarm_server.service.AlarmInfoService;
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
 * @packageName: club.yaso91.alarm_server.controller
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
        return alarmInfoService.addAlarmInfo(alarmInfo);
    }

    @RequestMapping("/loadCardReadersData")
    public List<CardReader> loadCardReadersData() {
        return alarmInfoService.loadCardReadersData();
    }

    @RequestMapping(value="/fixAlarmInfo",method = RequestMethod.POST)
    public boolean fixAlarmInfo(@RequestBody AlarmInfo alarmInfo) {
        return alarmInfoService.fixAlarmInfo(alarmInfo);
    }

    @RequestMapping(value = "/deleteAlarmInfos",method = RequestMethod.POST)
    public boolean deleteAlarmInfos(@RequestBody ArrayList<Integer> ids) {
        return alarmInfoService.deleteAlarmInfos(ids);
    }
}

