/*
 * Copyright(c)2002-2019, 雅俗工作室.
 *    项目名称:alarm_server
 *    文件名称:LogHandlerAop.java
 *    Date:2019/11/25 下午1:39
 *    Author:Yaso
 */
package club.yaso91.alarmserver.common.aop;

import club.yaso91.alarmserver.service.AlarmStateService;
import club.yaso91.alarmserver.service.component.ModbusCom;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 日志切面
 *
 * @author: Yaso
 * @data: 2019-11-22 20:41
 **/
@Aspect
@Slf4j
@Component
public class LogHandlerAop {

    /**
     * service层插入切入点
     */
    @Pointcut(value = "execution(public * club.yaso91.alarmserver.service.*.insert*(..))")
    public void dbInsertPc() {
    }

    /**
     * service层更新切入点
     */
    @Pointcut(value = "!execution(void club.yaso91.alarmserver.service.AlarmStateService.updateAlarmInfo())" +
            "&& execution(public * club.yaso91.alarmserver.service.*.update*(..))")
    public void dbUpdatePc() {
    }

    /**
     * service层删除切入点
     */
    @Pointcut(value = "execution(public * club.yaso91.alarmserver.service.*.delete*(..))")
    public void dbDeletePc() {
    }


    /**
     * 串口状态切入点和通知.
     */
    @After(value = "execution(public void club.yaso91.alarmserver.service.AlarmStateService.updateAlarmInfo())")
    public void logComState(JoinPoint joinPoint) {
        AlarmStateService alarmStateService = (AlarmStateService) joinPoint.getTarget();
        alarmStateService.getModbusManger().getComs().forEach((n) -> {
            String comState = n.getComState();
            if (ModbusCom.COMM_STATE_OPEN.equals(comState)) {
                log.info("{} 状态: {}. ", n.getPortName(), comState);
                return;
            }
            if (ModbusCom.COMM_STATE_CLOSE.equals(comState)) {
                log.error("{} 状态: {}. ", n.getPortName(), comState);
                return;
            }
        });
    }

    /**
     * 记录数据库改变的日志.
     *
     * @param jp
     */
    @AfterReturning(value = "dbInsertPc() || dbUpdatePc() || dbDeletePc()", returning = "result")
    public void logDbChange(JoinPoint jp, Object result) {
        String argsStr = "";
        Object[] args = jp.getArgs();
        for (Object arg : args) {
            argsStr += arg.toString();
        }
        if (result == null) {
            result = "";
        }
        log.info("数据库改变日志: -- method signature:{},args:{},result:{}.", jp.getSignature().toString(), argsStr,
                result.toString());
    }

}
