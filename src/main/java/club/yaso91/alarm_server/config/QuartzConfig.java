package club.yaso91.alarm_server.config;

import club.yaso91.alarm_server.service.AlarmStateService;
import org.quartz.CronTrigger;
import org.quartz.SimpleTrigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

@Configuration
public class QuartzConfig {
    @Autowired
    private AlarmStateService alarmStateService;

    @Bean
    MethodInvokingJobDetailFactoryBean jobDetail1() {
        MethodInvokingJobDetailFactoryBean bean =
                new MethodInvokingJobDetailFactoryBean();
        bean.setTargetBeanName("alarmStateService");
        bean.setTargetMethod("updateAlarmInfo");
        return bean;
    }

    @Bean
    MethodInvokingJobDetailFactoryBean jobDetail2() {
        MethodInvokingJobDetailFactoryBean bean =
                new MethodInvokingJobDetailFactoryBean();
        bean.setTargetBeanName("alarmStateService");
        bean.setTargetMethod("pushSumInfo");
        return bean;
    }

    @Bean
    SimpleTriggerFactoryBean simpleTrigger() {
        SimpleTriggerFactoryBean bean =
                new SimpleTriggerFactoryBean();
        bean.setJobDetail(jobDetail1().getObject());
        bean.setStartDelay(1000);
        bean.setRepeatInterval(1000);
        return bean;
    }

    @Bean
    CronTriggerFactoryBean cronTrigger() {
        CronTriggerFactoryBean bean =
                new CronTriggerFactoryBean();
        bean.setJobDetail(jobDetail2().getObject());
        bean.setCronExpression("0 58 20 * * ?");
        return bean;
    }

    @Bean
    SchedulerFactoryBean schedulerFactory() {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        SimpleTrigger simpleTrigger = simpleTrigger().getObject();
        CronTrigger cronTrigger = cronTrigger().getObject();
        bean.setTriggers(simpleTrigger, cronTrigger);
        return bean;
    }
}
