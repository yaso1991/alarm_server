package club.yaso91.alarm_server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class Manager {
    private long id;
    private String username;
    private String nickname;
    @JsonIgnore
    private String password;
}
