/**
 * projectName: alarm_server
 * fileName: AlarmItemInfoMapper.java
 * packageName: club.yaso91.alarm_server.mapper
 * date: 2019-10-07 15:50
 * copyright(c) 2017-2020 FuYun design studio.
 */
package club.yaso91.alarm_server.mapper;

import club.yaso91.alarm_server.entity.AlarmItemInfo;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * @version: V1.0
 * @author: Yaso
 * @className: AlarmItemInfoMapper
 * @packageName: club.yaso91.alarm_server.mapper
 * @description:
 * @data: 2019-10-07 15:50
 **/
@Repository
public interface AlarmItemInfoMapper {
    ArrayList<AlarmItemInfo> selectSumInfos(Timestamp beginTime, Timestamp endTime, String alarmName,
                                            String employeeName);

    int selectCount();

    AlarmItemInfo find(int alarmId, Timestamp alarmTime);


    int insert(AlarmItemInfo alarmItemInfo);

    int update(AlarmItemInfo alarmItemInfo);
}
