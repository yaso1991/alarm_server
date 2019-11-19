/*
 * Copyright(c)2002-2019, 雅俗工作室.
 *    项目名称:alarm_server
 *    文件名称:SystemConfigService.java
 *    Date:2019/11/17 上午11:13
 *    Author:Yaso
 */
package club.yaso91.alarmserver.service;

import club.yaso91.alarmserver.domain.SystemConfig;
import club.yaso91.alarmserver.mapper.SystemConfigMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
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
@CacheConfig(cacheNames = "c1")
public class SystemConfigService {
    @Autowired
    private SystemConfigMapper systemConfigMapper;

    /**
     * 加载系统配置
     * @return
     */
    @Cacheable(key = "#root.methodName")
    public SystemConfig loadSystemConfig() {
        return systemConfigMapper.selectAll();
    }

    @CachePut(key = "#systemConfig.id")
    public boolean updateSystemConfig(SystemConfig systemConfig) {
        return systemConfigMapper.update(systemConfig) == 1;


    }
}
