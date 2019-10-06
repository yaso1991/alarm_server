/**
 * projectName: alarm_server
 * fileName: AlarmInfoService.java
 * packageName: club.yaso91.alarm_server.service
 * date: 2019-10-05 19:02
 * copyright(c) 2017-2020 FuYun design studio.
 */
package club.yaso91.alarm_server.service;

import club.yaso91.alarm_server.entity.AlarmInfo;
import club.yaso91.alarm_server.mapper.AlarmInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @version: V1.0
 * @author: Yaso
 * @className: AlarmInfoService
 * @packageName: club.yaso91.alarm_server.service
 * @description:
 * @data: 2019-10-05 19:02
 **/
@Service
public class AlarmInfoService {
    @Autowired
    private AlarmInfoMapper alarmInfoMapper;

    public ArrayList<AlarmInfo> getAlarmInfos() {
        return alarmInfoMapper.selectAlarmInfos();
    }
}
