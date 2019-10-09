/**
 * projectName: alarm_server
 * fileName: SumInfoService.java
 * packageName: club.yaso91.alarm_server.service
 * date: 2019-10-07 15:47
 * copyright(c) 2017-2020 FuYun design studio.
 */
package club.yaso91.alarm_server.service;

import club.yaso91.alarm_server.entity.AlarmItemInfo;
import club.yaso91.alarm_server.mapper.AlarmItemInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * @version: V1.0
 * @author: Yaso
 * @className: SumInfoService
 * @packageName: club.yaso91.alarm_server.service
 * @description:
 * @data: 2019-10-07 15:47
 **/
@Service
public class SumInfoService {
    @Autowired
    AlarmItemInfoMapper alarmItemInfoMapper;

    public ArrayList<AlarmItemInfo> getSumInfos(Timestamp beginTime,Timestamp endTime,String alarmName,String employeeName) {
        return alarmItemInfoMapper.selectSumInfos(beginTime,endTime,alarmName,employeeName);
    }

}
