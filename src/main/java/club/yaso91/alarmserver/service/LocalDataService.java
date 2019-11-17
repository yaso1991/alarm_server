/*
 * Copyright(c)2002-2019, 雅俗工作室.
 *    项目名称:alarm_server
 *    文件名称:LocalDataService.java
 *    Date:2019/11/17 上午11:13
 *    Author:Yaso
 */
package club.yaso91.alarmserver.service;

import club.yaso91.alarmserver.domain.SystemConfig;
import lombok.Data;
import org.springframework.stereotype.Service;

/**
 * @version: V1.0
 * @author: Yaso
 * @className: LoacalDataService
 * @packageName: club.yaso91.alarmserver.service
 * @description:
 * @data: 2019-11-01 17:17
 **/
@Service
@Data
public class LocalDataService {
    private SystemConfig localSystemConfig = null;
}
