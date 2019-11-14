package club.yaso91.alarmserver.mapper;

import club.yaso91.alarmserver.entity.AlarmInfo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface AlarmInfoMapper {
    ArrayList<AlarmInfo> selectAlarmInfos();

    int insertAlarmInfo(AlarmInfo alarmInfo);

    int updateAlarmInfo(AlarmInfo alarmInfo);

    int deleteAlarmInfos(ArrayList<Integer> ids);

    int updateAlarming(AlarmInfo alarmInfo);

    int updateStateIsNormal(String comPort, String pointName);

    int findId(String comPort, String pointName);

    AlarmInfo find(String comPort, String pointName);

    int updateStateAlarmed(AlarmInfo alarmInfo);
}
