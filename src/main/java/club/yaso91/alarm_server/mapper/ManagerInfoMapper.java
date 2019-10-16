package club.yaso91.alarm_server.mapper;

import club.yaso91.alarm_server.entity.Manager;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerInfoMapper {
    Manager selectManager(String username, String password);

    Manager selectUserByUsername(String username);
}
