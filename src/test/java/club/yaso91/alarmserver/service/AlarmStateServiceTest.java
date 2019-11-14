package club.yaso91.alarmserver.service;

import club.yaso91.alarmserver.mapper.AlarmItemInfoMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AlarmStateServiceTest {
    @Autowired
    AlarmStateService alarmStateService;

    @Autowired
    AlarmItemInfoMapper alarmItemInfoMapper;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testClass() {
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateAlarmInfo() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    alarmStateService.updateAlarmInfo();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();

        try {
            Thread.currentThread().join(90000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkAndPushSumInfo() {
        alarmStateService.checkAndPushSumInfo();
    }
}
