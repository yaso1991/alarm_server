/*
 * Copyright(c)2002-2019, 雅俗工作室.
 *    项目名称:alarm_server
 *    文件名称:SystemController.java
 *    Date:2019/11/17 下午5:11
 *    Author:Yaso
 */
package club.yaso91.alarmserver.controller;

import club.yaso91.alarmserver.service.AlarmStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

/**
 * @version: V1.0
 * @author: Yaso
 * @className: SystemController
 * @packageName: club.yaso91.alarmserver.controller
 * @description: 系统内部控制器
 * @data: 2019-11-17 17:11
 **/
@Controller
public class SystemController {
    @Autowired
    AlarmStateService alarmStateService;

    @Scheduled(initialDelay = 10000,fixedDelay = 10000000)
    public void startModbus() {
        alarmStateService.initAndStartModbusCommunication();
    }

    @Scheduled(initialDelay = 10000, fixedDelay = 1000)
    public void updateAlarmInfoOnCycle() {
        alarmStateService.updateAlarmInfo();
    }

    @Scheduled(cron = "0/50 * * * * ?")
    public void pushSumInfoOnSettingTime() {
        alarmStateService.checkAndPushSumInfo();
    }
}
