package club.yaso91.alarmserver.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AlarmStateServiceTest {
    @Autowired
    AlarmStateService alarmStateService;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void checkAndPushSumInfo() throws IOException {
        alarmStateService.checkAndPushSumInfo();
    }


    @Test
    public void pushSumInfo() {
//        alarmStateService.pushSumInfo();
        try {
            Thread.currentThread().join(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void init() {
//        assertEquals(16,alarmStateService.getModbusManger().getComs().size());
    }
}
