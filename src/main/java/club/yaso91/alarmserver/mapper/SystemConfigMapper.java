package club.yaso91.alarmserver.mapper;

import club.yaso91.alarmserver.entity.SystemConfig;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemConfigMapper {

    SystemConfig selectAll();

    int update(SystemConfig systemConfig);
}
