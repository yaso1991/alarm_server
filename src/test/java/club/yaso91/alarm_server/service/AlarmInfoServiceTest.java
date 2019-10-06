package club.yaso91.alarm_server.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class AlarmInfoServiceTest {
    @Autowired
    private AlarmInfoService alarmInfoService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAlarmInfos() {
        assertEquals(true, alarmInfoService.getAlarmInfos() != null);
        assertEquals("员工A", alarmInfoService.getAlarmInfos().get(0).getEmployee().getName());
    }
}
