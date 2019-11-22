/*
 * Copyright(c)2002-2019, 雅俗工作室.
 *    项目名称:alarm_server
 *    文件名称:EmailSender.java
 *    Date:2019/11/22 下午4:45
 *    Author:Yaso
 */
package club.yaso91.alarmserver.common.util;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @packageName: club.yaso91.alarmserver.service.component
 * @description: 邮件控制类
 * @data: 2019-10-17 18:25
 **/
@Component
public class EmailSender {
    @Autowired
    private JavaMailSender mailSender;
    private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 60L,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(10),
            new ThreadFactoryBuilder().setNameFormat("emailSender").build());

    /**
     * 推送报警信息邮件.
     *
     * @param from
     * @param to
     * @param subject
     * @param context
     * @param cc      抄送
     */
    public void sendMail(String from, String to, String subject, String context,
                         String... cc) {
        sendMail(null, from, to, subject, context, cc);
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
    public void sendMail(File file, String from, String to, String subject,
                         String context,
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
                    if (file != null) {
                        helper.addAttachment(file.getName(), file);
                    }
                    mailSender.send(message);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
