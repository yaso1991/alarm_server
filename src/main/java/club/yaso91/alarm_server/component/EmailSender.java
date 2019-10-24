/**
 * projectName: alarm_server
 * fileName: EmailSender.java
 * packageName: club.yaso91.alarm_server.component
 * date: 2019-10-17 18:25
 * copyright(c) 2017-2020 FuYun design studio.
 */
package club.yaso91.alarm_server.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * @version: V1.0
 * @author: Yaso
 * @className: EmailSender
 * @packageName: club.yaso91.alarm_server.component
 * @description: 邮件控制类
 * @data: 2019-10-17 18:25
 **/
@Component
public class EmailSender {
    @Autowired
    JavaMailSender mailSender;

    public void sendPushMail(String from, String to, String subject, String context, String... cc) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
                simpleMailMessage.setFrom(from);
                simpleMailMessage.setTo(to);
                simpleMailMessage.setCc(cc);
                simpleMailMessage.setSubject(subject);
                simpleMailMessage.setText(context);
                mailSender.send(simpleMailMessage);
            }
        },"emailThread_" + Thread.currentThread().getId()).start();

    }


}
