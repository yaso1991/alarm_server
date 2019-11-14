/*
 * Copyright(c)2002-2019, 雅俗工作室.
 *    项目名称:alarm_server
 *    文件名称:AlarmServerApplication.java
 *    Date:2019/11/14 下午3:50
 *    Author:Yaso
 */

package club.yaso91.alarmserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
/**
 * @title: AlarmServerApplication
 * @package club.yaso91.alarmserver
 * @description: springboot启动入口.
 * @author: Yaso
 * @date: 2019-11-14 15:50
 * @version: V1.0
*/

@MapperScan("club.yaso91.alarmserver.mapper")
@SpringBootApplication
@EnableScheduling
public class AlarmServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlarmServerApplication.class, args);
    }

}
