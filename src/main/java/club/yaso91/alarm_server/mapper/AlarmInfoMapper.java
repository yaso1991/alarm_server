package club.yaso91.alarm_server.mapper;

import club.yaso91.alarm_server.entity.AlarmInfo;
import club.yaso91.alarm_server.entity.CardReader;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface AlarmInfoMapper {
    ArrayList<AlarmInfo> selectAlarmInfos();
    int insertAlarmInfo(AlarmInfo alarmInfo);
    int updateAlarmInfo(AlarmInfo alarmInfo);

    int deleteAlarmInfos(ArrayList<Integer> ids);
}
