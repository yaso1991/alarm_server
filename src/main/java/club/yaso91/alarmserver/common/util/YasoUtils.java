/*
 * Copyright(c)2002-2019, 雅俗工作室.
 *    项目名称:alarm_server
 *    文件名称:YasoUtils.java
 *    Date:2019/11/22 下午3:33
 *    Author:Yaso
 */
package club.yaso91.alarmserver.common.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Yaso的工具类
 * @author: Yaso
 * @date: 2019-11-22 14:53
 */

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
                LocalDateTime.of(now.getYear(), now.getMonthValue(), now.getDayOfMonth(), 0, 0,
                        0).minusHours(24);
        return yestoday.toEpochSecond(ZoneOffset.ofHours(8));
    }
}
