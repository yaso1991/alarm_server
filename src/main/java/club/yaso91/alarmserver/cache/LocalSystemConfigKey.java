/*
 * Copyright(c)2002-2019, 雅俗工作室.
 *    项目名称:alarm_server
 *    文件名称:LocalSystemConfigKey.java
 *    Date:2019/11/19 上午8:24
 *    Author:Yaso
 */

package club.yaso91.alarmserver.cache;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


@Component
public class LocalSystemConfigKey implements KeyGenerator {

    @Override
    public Object generate(Object target, Method method, Object... params) {
        return "systemConfig";
    }
}
