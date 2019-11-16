package club.yaso91.client.util;

import org.junit.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class YasoUtilsTest {

    @Test
    public void getYestodayMills() {
        long yestodayMills = YasoUtils.getYestodayMills()*1000;
        System.out.println(new Timestamp(yestodayMills).toString());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(yestodayMills, 0, ZoneOffset.UTC);
        System.out.println(localDateTime.format(dateTimeFormatter));

    }
}
