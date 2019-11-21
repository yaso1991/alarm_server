/*
 * Copyright(c)2002-2019, 雅俗工作室.
 *    项目名称:alarm_server
 *    文件名称:LogAspect.java
 *    Date:2019/11/20 上午9:48
 *    Author:Yaso
 */
package club.yaso91.alarmserver.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @version: V1.0
 * @author: Yaso
 * @className: LogAspect
 * @packageName: club.yaso91.alarmserver.aop
 * @description: 日志切面
 * @data: 2019-11-20 9:48
 **/

@Component
@Aspect
@Slf4j
public class LogAspect {
    @Pointcut("execution(* club.yaso91.alarmserver.*.*.*(..))")
    public void webLog() {
    }

//    @Around(value = "webLog()")
//    public void aroundWebLog(ProceedingJoinPoint pjp) {
//        try {
//            long l = System.currentTimeMillis();
//
//            pjp.proceed();
//            System.out.println(pjp.getSignature().getName() + " execution spend time:" + (System.currentTimeMillis() - l));
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
//    }

    @AfterThrowing(value = "webLog()",throwing = "e")
    public void throwWebLog(JoinPoint joinPoint,Exception e) {
        Throwable cause = e.getCause();
        log.error(cause.toString()  + ":" + e.toString());
        System.out.println(cause.toString()  + ":" + e.toString());
    }


}
