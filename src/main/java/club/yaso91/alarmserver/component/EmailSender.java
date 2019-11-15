/**
 * projectName: alarmserver
 * fileName: EmailSender.java
 * packageName: club.yaso91.alarmserver.component
 * date: 2019-10-17 18:25
 * copyright(c) 2017-2020 FuYun design studio.
 */
package club.yaso91.alarmserver.component;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @version: V1.0
 * @author: Yaso
 * @className: EmailSender
 * @packageName: club.yaso91.alarmserver.component
 * @description: 邮件控制类
 * @data: 2019-10-17 18:25
 **/
@Component
public class EmailSender {
    @Autowired
    private JavaMailSender mailSender;
    private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 60L, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(10), new ThreadFactoryBuilder().setNameFormat("emailSender").build());

    /**
     * 推送报警信息邮件.
     *
     * @param from
     * @param to
     * @param subject
     * @param context
     * @param cc      抄送
     */
    public void sendAlarmMail(String from, String to, String subject, String context, String... cc) {
        threadPoolExecutor.submit(new Runnable() {
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
        });
    }

    /**
     * 推送前日汇总邮件.
     *
     * @param file
     * @param from
     * @param to
     * @param subject
     * @param context
     * @param cc
     */
    public void sendSumInfoMail(File file, String from, String to, String subject, String context,
                                String... cc) {
        threadPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    MimeMessage message = mailSender.createMimeMessage();
                    MimeMessageHelper helper = new MimeMessageHelper(message, true);
                    helper.setFrom(from);
                    helper.setTo(to);
                    helper.setCc(cc);
                    helper.setSubject(subject);
                    helper.setText(context);
                    helper.addAttachment(file.getName(), file);
                    mailSender.send(message);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
