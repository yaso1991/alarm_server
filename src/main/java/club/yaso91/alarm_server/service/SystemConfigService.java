/**
 * projectName: alarm_server
 * fileName: SystemConfigService.java
 * packageName: club.yaso91.alarm_server.service
 * date: 2019-10-11 13:59
 * copyright(c) 2017-2020 FuYun design studio.
 */
package club.yaso91.alarm_server.service;

import club.yaso91.alarm_server.entity.SystemConfig;
import club.yaso91.alarm_server.mapper.SystemConfigMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @version: V1.0
 * @author: Yaso
 * @className: SystemConfigService
 * @packageName: club.yaso91.alarm_server.service
 * @description:
 * @data: 2019-10-11 13:59
 **/
@Service
@Data
public class SystemConfigService {
    private SystemConfigMapper systemConfigMapper;
    private LocalDataService localDataService;

    @Autowired
    public SystemConfigService(SystemConfigMapper systemConfigMapper,LocalDataService localDataService) {
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
