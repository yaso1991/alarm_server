/**
 * projectName: alarm_server
 * fileName: SystemConfigController.java
 * packageName: club.yaso91.alarm_server.controller
 * date: 2019-10-11 13:52
 * copyright(c) 2017-2020 FuYun design studio.
 */
package club.yaso91.alarm_server.controller;

import club.yaso91.alarm_server.entity.SystemConfig;
import club.yaso91.alarm_server.service.SystemConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @version: V1.0
 * @author: Yaso
 * @className: SystemConfigController
 * @packageName: club.yaso91.alarm_server.controller
 * @description:
 * @data: 2019-10-11 13:52
 **/
@RestController
@RequestMapping("/systemInfo")
public class SystemConfigController {
    @Autowired
    SystemConfigService systemConfigService;

    @RequestMapping
    public SystemConfig home() {
        return systemConfigService.loadSystemConfig();
    }

    @RequestMapping(value="/updateSystemConfig",method = RequestMethod.POST)
    public boolean updateSystemConfig(@RequestBody SystemConfig systemConfig) {
        return  systemConfigService.updateSystemConfig(systemConfig);
    }
}