package club.yaso91.alarm_server.mapper;

import club.yaso91.alarm_server.entity.SystemConfig;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemConfigMapper {

    SystemConfig selectAll();

    int update(SystemConfig systemConfig);
}
