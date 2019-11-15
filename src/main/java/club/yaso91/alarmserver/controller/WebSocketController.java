/**
 * projectName: alarmserver
 * fileName: WebSocketController.java
 * packageName: club.yaso91.alarmserver.controller
 * date: 2019-11-11 17:20
 * copyright(c) 2017-2020 FuYun design studio.
 */
package club.yaso91.alarmserver.controller;

import club.yaso91.alarmserver.entity.AlarmInfo;
import club.yaso91.alarmserver.service.AlarmInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * @version: V1.0
 * @author: Yaso
 * @className: WebSocketController
 * @packageName: club.yaso91.alarmserver.controller
 * @description:
 * @data: 2019-11-11 17:20
 **/
@RestController
public class WebSocketController {
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    AlarmInfoService alarmInfoService;

    @Scheduled(initialDelay = 10000, fixedDelay = 1000)
    public void greeting(){
        ArrayList<AlarmInfo> alarmInfos = alarmInfoService.getAlarmInfos();
        simpMessagingTemplate.convertAndSend("/topic/greetings", alarmInfos);
    }
}
