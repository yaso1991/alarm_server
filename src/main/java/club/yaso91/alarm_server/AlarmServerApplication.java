package club.yaso91.alarm_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
public class AlarmServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(AlarmServerApplication.class, args);
  }

}
