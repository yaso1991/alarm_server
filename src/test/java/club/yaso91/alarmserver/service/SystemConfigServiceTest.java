/*
 * Copyright(c)2002-2019, 雅俗工作室.
 *    项目名称:alarm_server
 *    文件名称:SystemConfigServiceTest.java
 *    Date:2019/11/18 下午5:45
 *    Author:Yaso
 */

package club.yaso91.alarmserver.service;

import club.yaso91.alarmserver.domain.SystemConfig;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SystemConfigServiceTest {
    @Autowired
    SystemConfigService systemConfigService;
    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void loadSystemConfig() {
        System.out.println(systemConfigService.loadSystemConfig().toString());
        System.out.println(systemConfigService.loadSystemConfig().toString());
    }

    @Test
    public void updateSystemConfig() {
        SystemConfig systemConfig = systemConfigService.loadSystemConfig();
        systemConfig.setManagerPushDelay(5300);
        systemConfigService.updateSystemConfig(systemConfig);
        SystemConfig systemConfig1 = (SystemConfig) redisTemplate.opsForValue().get("c1::localSystemConfig");
        System.out.println(systemConfig1.getSumPushTime().toLocalTime());
        systemConfig1.setId(9);
        redisTemplate.opsForValue().getAndSet ("c1::localSystemConfig",systemConfig1);
        SystemConfig systemConfig2 = (SystemConfig) redisTemplate.opsForValue().get("c1::localSystemConfig");
        System.out.println(systemConfig2.getId());

    }
}
