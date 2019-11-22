/*
 * Copyright(c)2002-2019, 雅俗工作室.
 *    项目名称:alarm_server
 *    文件名称:ControllerHandlerAOP.java
 *    Date:2019/11/22 下午3:33
 *    Author:Yaso
 */

package club.yaso91.alarmserver.common.aop;

import club.yaso91.alarmserver.common.bean.ResultBean;
import club.yaso91.alarmserver.common.exceptions.NoPermissionException;
import club.yaso91.alarmserver.common.exceptions.UnloginException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;


/**
 * 异常统一处理和异常日志记录切面.
 * @author 晓风轻 (Yaso改)
 * @date: 2019-11-22 14:51
 */
@Slf4j
@Component
@Aspect
public class ControllerHandlerAOP {

    /**
     * controller切入点.
     */
    @Pointcut(value = " execution(public * club.yaso91.alarmserver.controller.*.*(..))")
    public void pc() {}

    /**
     * 性能日志记录.
     * @param pjp
     * @return
     */
    @Around(value = "pc()")
    public Object handlerControllerMethod(ProceedingJoinPoint pjp) {
//        ResultBean<?> result;
//        try {
//            long startTime = System.currentTimeMillis();
//            result = (ResultBean<?>) pjp.proceed();
//            long elapsedTime = System.currentTimeMillis() - startTime;
//            log.info("[{}]use time: {}", pjp.getSignature(), elapsedTime);
//        } catch (Throwable e) {
//            result = handlerException(pjp, e);
//        }
//        return result;

        Object proceed = null;
        try {
          proceed   = pjp.proceed();

        } catch (Throwable e) {
            handlerException(pjp, e);
        }
        return proceed;
    }

    /**
     * 异常统一处理和异常日志打印.
     *
     * @param pjp
     * @param e
     * @return
     */
    private ResultBean<?> handlerException(ProceedingJoinPoint pjp, Throwable e) {
        ResultBean<?> result = new ResultBean();

        // 校验出错，参数非法
        // 已知异常.unchecked Exception.
        if (e instanceof RuntimeException || e instanceof IllegalArgumentException) {
            log.error("--------------------------->>");
            log.error(e.toString());
            List<StackTraceElement> stackTraceElements = Arrays.asList(e.getStackTrace());
            for (StackTraceElement stackTraceElement: stackTraceElements) {
                log.error(stackTraceElement.toString());
            }
            result.setMsg(e.getLocalizedMessage());
            result.setCode(ResultBean.CHECK_FAIL);
        }
        // 没有登陆
        else if (e instanceof UnloginException) {
            result.setMsg("Unlogin");
            result.setCode(ResultBean.NO_LOGIN);
        }
        // 没有权限
        else if (e instanceof NoPermissionException) {
            result.setMsg("NO PERMISSION");
            result.setCode(ResultBean.NO_PERMISSION);
        }
        // TODO 未知的异常，应该格外注意，可以发送邮件通知等
        else {
            log.error(pjp.getSignature() + " error ", e);
            result.setMsg(e.toString());
            result.setCode(ResultBean.UNKNOWN_EXCEPTION);
        }
        return result;
    }
}
