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
public class SystemConfigService {
    private SystemConfigMapper systemConfigMapper;
    // FIXME 这里 service要不要调用另外一个service?
    private LocalDataService localDataService;

    @Autowired
    public SystemConfigService(SystemConfigMapper systemConfigMapper, LocalDataService localDataService) {
        this.systemConfigMapper = systemConfigMapper;
        this.localDataService = localDataService;
        this.localDataService.setLocalSystemConfig(this.systemConfigMapper.selectAll());
    }

    public SystemConfig loadSystemConfig() {
        return systemConfigMapper.selectAll();
    }

    public boolean updateSystemConfig(SystemConfig systemConfig) {
        boolean result = false;
        result = systemConfigMapper.update(systemConfig) == 1;
        if (result) {
            this.localDataService.setLocalSystemConfig(loadSystemConfig());
        }
        return result;

    }
}
