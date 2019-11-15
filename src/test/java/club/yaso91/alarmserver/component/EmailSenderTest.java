package club.yaso91.alarmserver.component;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailSenderTest {

    @Autowired
    private EmailSender emailSender;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void sendPushMail() {
        emailSender.sendAlarmMail("1441825297@qq.com","1721662545@qq.com","你好","你好,御厨哦哦");
    }
}
