package club.yaso91.alarmserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan("club.yaso91.alarmserver.mapper")
@SpringBootApplication
@EnableScheduling
public class AlarmServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlarmServerApplication.class, args);
    }

}
