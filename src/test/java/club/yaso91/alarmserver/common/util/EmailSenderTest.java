/*
 * Copyright(c)2002-2019, 雅俗工作室.
 *    项目名称:alarm_server
 *    文件名称:EmailSenderTest.java
 *    Date:2019/11/22 下午8:10
 *    Author:Yaso
 */

package club.yaso91.alarmserver.common.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailSenderTest {
    @Autowired
    EmailSender emailSender;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void sendMail() {
    }

    @Test
    public void testSendMail() {
        File file = new File("d:/fuck.xls");

        emailSender.sendMail(file, "1441825297@qq.com", "1721662545@qq.com", "你好", "哈哈",
                "1441825297@qq.com");
        try {
            Thread.currentThread().join(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
