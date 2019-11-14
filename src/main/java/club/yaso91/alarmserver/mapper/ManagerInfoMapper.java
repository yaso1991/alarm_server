package club.yaso91.alarmserver.mapper;

import club.yaso91.alarmserver.entity.Manager;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerInfoMapper {
    Manager selectManager(String username, String password);

    Manager selectUserByUsername(String username);
}
