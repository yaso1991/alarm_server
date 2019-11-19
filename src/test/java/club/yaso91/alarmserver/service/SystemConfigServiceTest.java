/*
 * Copyright(c)2002-2019, 雅俗工作室.
 *    项目名称:alarm_server
 *    文件名称:SystemConfigServiceTest.java
 *    Date:2019/11/18 下午5:45
 *    Author:Yaso
 */

package club.yaso91.alarmserver.service;

import club.yaso91.alarmserver.domain.SystemConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SystemConfigServiceTest {
    @Autowired
    SystemConfigService systemConfigService;
    @Test
    public void loadSystemConfig() {
        System.out.println(systemConfigService.loadSystemConfig().toString());
        System.out.println(systemConfigService.loadSystemConfig().toString());
    }

    @Test
    public void updateSystemConfig() {
        SystemConfig systemConfig = systemConfigService.loadSystemConfig();
        systemConfig.setManagerPushDelay(1200);
        systemConfigService.updateSystemConfig(systemConfig);
    }
}
