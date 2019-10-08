package club.yaso91.alarm_server.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class SumInfoServiceTest {
    @Autowired
    private SumInfoService sumInfoService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getSumInfos() {
        System.out.println(sumInfoService.getSumInfos(1));
        assertEquals("7号线中轧测厚", sumInfoService.getSumInfos(1).get(1).getAlarmInfo().getName());
    }
}
