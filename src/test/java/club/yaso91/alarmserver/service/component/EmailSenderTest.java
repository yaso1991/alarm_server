package club.yaso91.alarmserver.service.component;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailSenderTest {

    @Autowired
    private EmailSender emailSender;

    @Before
    public void setUp() throws Exception {
    }



    @Test
    public void sendAlarmMail() throws InterruptedException {
        emailSender.sendAlarmMail("1441825297@qq.com", "1721662545@qq.com", "你好", "你好,御厨哦哦");
        Thread.currentThread().join(5000);
    }

    @Test
    public void sendSumInfoMail() throws InterruptedException {
        File file = new File("D:/test/testExcel_1570611864507.xls");
        if(file.exists()) {
            emailSender.sendSumInfoMail(file,"1441825297@qq.com", "1721662545@qq.com", "你好", "你好,御厨哦哦");
            Thread.currentThread().join(5000);
        }
    }
}
