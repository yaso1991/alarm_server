/**
 * projectName: alarm_server
 * fileName: YasoUtils.java
 * packageName: club.yaso91.alarm_server.common
 * date: 2019-10-16 20:37
 * copyright(c) 2017-2020 FuYun design studio.
 */
package club.yaso91.alarm_server.common;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @version: V1.0
 * @author: Yaso
 * @className: YasoUtils
 * @packageName: club.yaso91.alarm_server.common
 * @description: Yaso的工具类.
 * @data: 2019-10-16 20:37
 **/
public class YasoUtils {
    public static String genarateBCryptPasswordEncoder(String password,int encoderKey){
        return new BCryptPasswordEncoder(4).encode(password);
    }
}
