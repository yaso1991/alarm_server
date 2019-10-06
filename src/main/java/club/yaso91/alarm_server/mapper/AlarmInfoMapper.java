package club.yaso91.alarm_server.mapper;

import club.yaso91.alarm_server.entity.AlarmInfo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface AlarmInfoMapper {
    ArrayList<AlarmInfo> selectAlarmInfos();
}
