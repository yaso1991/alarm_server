package club.yaso91.alarmserver.service;

import club.yaso91.alarmserver.entity.AlarmItemInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class SumInfoServiceTest {
    @Autowired
    private SumInfoService sumInfoService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getSumInfos() {
        ArrayList<AlarmItemInfo> sumInfos = sumInfoService.getSumInfos(null, null, null, null);
        System.out.println(sumInfos.toString());
        assertEquals(true, !sumInfos.isEmpty());

        Calendar calendarBegin = Calendar.getInstance();
        calendarBegin.set(2019,8,25);
        Timestamp begin = new Timestamp(calendarBegin.getTimeInMillis());
        Calendar calendarEnd = Calendar.getInstance();
        calendarEnd.set(2019,8,28);
        Timestamp end = new Timestamp(calendarEnd.getTimeInMillis());

        sumInfos = sumInfoService.getSumInfos(begin, end, null, null);
        System.out.println(sumInfos.toString());
        assertEquals(true, !sumInfos.isEmpty());

        sumInfos = sumInfoService.getSumInfos(null, null, "7号线中轧测厚", null);
        System.out.println(sumInfos.toString());
        assertEquals(true, !sumInfos.isEmpty());

        sumInfos = sumInfoService.getSumInfos(null, null, null, "员工A");
        System.out.println(sumInfos.toString());
        assertEquals(true, !sumInfos.isEmpty());






    }

    @Test
    public void exportSumInfos() throws IOException, ParseException ,Exception{
        assertEquals(true, sumInfoService.exportSumInfos() != null);
    }
}
