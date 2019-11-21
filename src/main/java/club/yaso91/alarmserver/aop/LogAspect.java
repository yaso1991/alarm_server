/*
 * Copyright(c)2002-2019, 雅俗工作室.
 *    项目名称:alarm_server
 *    文件名称:GobalExceptionHandler.java
 *    Date:2019/11/21 下午3:29
 *    Author:Yaso
 */
package club.yaso91.alarmserver.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @version: V1.0
 * @author: Yaso
 * @className: GobalExceptionHandler
 * @packageName: club.yaso91.alarmserver.gobal
 * @description: 异常统一处理
 * @data: 2019-11-21 14:49
 **/

@Aspect
@Component
@Slf4j
public class LogAspect {
    @Pointcut("execution(public club.yaso91.alarmserver.service.component.ModbusCom.initAndOpenCom")
    public void modbusStatePc() {
    }

    @AfterThrowing(value = "modbusStatePc()", throwing = "e")
    public void handleIOException(Exception e) {
//        log.error( Arrays.asList(e.getStackTrace()).toString());
    }
}
