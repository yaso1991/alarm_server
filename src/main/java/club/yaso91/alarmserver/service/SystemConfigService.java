/*
 * Copyright(c)2002-2019, 雅俗工作室.
 *    项目名称:alarm_server
 *    文件名称:SystemConfigService.java
 *    Date:2019/11/17 上午11:13
 *    Author:Yaso
 */
package club.yaso91.alarmserver.service;

import club.yaso91.alarmserver.cache.LocalSystemConfigKey;
import club.yaso91.alarmserver.domain.SystemConfig;
import club.yaso91.alarmserver.mapper.SystemConfigMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @version: V1.0
 * @author: Yaso
 * @className: SystemConfigService
 * @packageName: club.yaso91.alarmserver.service
 * @description:
 * @data: 2019-10-11 13:59
 **/
@Service
@Data
@CacheConfig(cacheNames = "localData")
public class SystemConfigService {
    @Autowired
    private SystemConfigMapper systemConfigMapper;
    @Autowired
    private LocalSystemConfigKey localSystemConfigKey;

    /**
     * 加载系统配置
     *
     * @return
     */
    @Cacheable(keyGenerator = "localSystemConfigKey")
    public SystemConfig loadSystemConfig() {
        return systemConfigMapper.selectAll();
    }

    @CachePut(keyGenerator = "localSystemConfigKey",condition = "#result != null")
    public SystemConfig updateSystemConfig(SystemConfig systemConfig) {
        if (systemConfigMapper.update(systemConfig) == 1) {
            return systemConfig;
        }
        return null;
    }

}
