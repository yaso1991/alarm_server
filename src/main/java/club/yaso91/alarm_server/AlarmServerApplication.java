package club.yaso91.alarm_server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("club.yaso91.alarm_server.mapper")
@SpringBootApplication
public class AlarmServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlarmServerApplication.class, args);
    }

}
