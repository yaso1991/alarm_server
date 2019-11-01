/**
 * projectName: alarm_server
 * fileName: LoacalDataService.java
 * packageName: club.yaso91.alarm_server.service
 * date: 2019-11-01 17:17
 * copyright(c) 2017-2020 FuYun design studio.
 */
package club.yaso91.alarm_server.service;

import club.yaso91.alarm_server.entity.SystemConfig;
import lombok.Data;
import org.springframework.stereotype.Service;

/**
 * @version: V1.0
 * @author: Yaso
 * @className: LoacalDataService
 * @packageName: club.yaso91.alarm_server.service
 * @description:
 * @data: 2019-11-01 17:17
 **/
@Service
@Data
public class LocalDataService {
    private SystemConfig localSystemConfig = null;
}
