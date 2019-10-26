/**
 * projectName: alarm_server
 * fileName: YasoUtils.java
 * packageName: club.yaso91.alarm_server.common
 * date: 2019-10-16 20:37
 * copyright(c) 2017-2020 FuYun design studio.
 */
package club.yaso91.alarm_server.common;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Timestamp;
import java.util.Calendar;

/**
 * @version: V1.0
 * @author: Yaso
 * @className: YasoUtils
 * @packageName: club.yaso91.alarm_server.common
 * @description: Yaso的工具类.
 * @data: 2019-10-16 20:37
 **/
public class YasoUtils {
    public static String genarateBCryptPasswordEncoder(String password, int encoderKey) {
        return new BCryptPasswordEncoder(4).encode(password);
    }

    /**
     * 返回昨天的时间值,从昨天00:00:00.000开始
     * @return 昨天的时间戳,00:00:00.000开始
     */
    public static long getYestodayMills() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis()-86400000);
        int date = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        return new Timestamp(year-1900,month,date,0,0,0,0).getTime();
    }
}
