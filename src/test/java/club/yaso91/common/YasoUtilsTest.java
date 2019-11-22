/*
 * Copyright(c)2002-2019, 雅俗工作室.
 *    项目名称:alarm_server
 *    文件名称:YasoUtilsTest.java
 *    Date:2019/11/22 下午1:46
 *    Author:Yaso
 */

package club.yaso91.common;

import club.yaso91.alarmserver.common.util.YasoUtils;
import org.junit.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

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
