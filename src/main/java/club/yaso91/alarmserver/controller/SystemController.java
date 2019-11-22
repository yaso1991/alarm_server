/*
 * Copyright(c)2002-2019, 雅俗工作室.
 *    项目名称:alarm_server
 *    文件名称:SystemController.java
 *    Date:2019/11/17 下午5:11
 *    Author:Yaso
 */
package club.yaso91.alarmserver.controller;

import club.yaso91.alarmserver.service.AlarmStateService;
import club.yaso91.alarmserver.service.SystemConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.io.IOException;

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

    @Autowired
    SystemConfigService systemConfigService;

    @Scheduled(initialDelay = 10000, fixedDelay = 10000000)
    public void startModbus() {
        alarmStateService.initAndStartModbusCommunication();
    }

    @Scheduled(initialDelay = 10000, fixedDelay = 1000)
    public void updateAlarmInfoOnCycle() {
        alarmStateService.updateAlarmInfo();

    }

    @Scheduled(cron = "0 * * * * ?")
    public void pushSumInfoOnSettingTime() throws IOException {
        alarmStateService.checkAndPushSumInfo();
    }

    /**
     * 启动时,读取一次系统设置到本地缓存.
     */
    @Scheduled(initialDelay = 5000, fixedDelay = 1000000000)
    public void reloadSystemConfig() {
        systemConfigService.loadSystemConfig();
    }
}
