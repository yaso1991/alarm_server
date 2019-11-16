/**
 * projectName: alarmserver
 * fileName: YasoUtils.java
 * packageName: club.yaso91.alarmserver.common
 * date: 2019-10-16 20:37
 * copyright(c) 2017-2020 FuYun design studio.
 */
package club.yaso91.client.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @version: V1.0
 * @author: Yaso
 * @className: YasoUtils
 * @packageName: club.yaso91.alarmserver.common
 * @description: Yaso的工具类.
 * @data: 2019-10-16 20:37
 **/
public class YasoUtils {
    /**
     * 生成密码的BCrypt编码.
     *
     * @param password
     * @param encoderKey
     * @return
     */
    public static String genarateBcryptPasswordEncoder(String password, int encoderKey) {
        return new BCryptPasswordEncoder(4).encode(password);
    }

    /**
     * 返回昨天的时间值,从昨天00:00:00.000开始
     *
     * @return 昨天的时间戳, 00:00:00.000开始,注意是秒为单位.如果使用毫秒,需要乘以1000.
     */
    public static long getYestodayMills() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime yestoday =
                LocalDateTime.of(now.getYear(), now.getMonthValue(), now.getDayOfMonth(), 0, 0, 0).minusHours(24);
        return yestoday.toEpochSecond(ZoneOffset.ofHours(8));
    }
}
