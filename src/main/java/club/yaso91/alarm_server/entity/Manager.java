package club.yaso91.alarm_server.entity;

import lombok.Data;

@Data
public class Manager {
    private long id;
    private String username;
    private String nickname;
    private String password;
    private String power;
}
