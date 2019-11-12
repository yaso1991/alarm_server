/**
 * projectName: alarm_server
 * fileName: WebSocketController.java
 * packageName: club.yaso91.alarm_server.controller
 * date: 2019-11-11 17:20
 * copyright(c) 2017-2020 FuYun design studio.
 */
package club.yaso91.alarm_server.controller;

import club.yaso91.alarm_server.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @version: V1.0
 * @author: Yaso
 * @className: WebSocketController
 * @packageName: club.yaso91.alarm_server.controller
 * @description:
 * @data: 2019-11-11 17:20
 **/
@RestController
public class WebSocketController {
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;
    @Scheduled(initialDelay = 10000, fixedDelay = 1000)
    public void greeting() throws Exception{
        Message message = new Message();
        message.setContent("son of a bitch");
        message.setName("hhe");
        simpMessagingTemplate.convertAndSend("/topic/greetings",message);
    }
}
