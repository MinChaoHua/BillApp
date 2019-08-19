package com.bill.system.timing;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.scheduling.support.CronTrigger;
import java.util.Date;

@Configuration
@EnableScheduling //开启定时
public class DynamicScheduleTask implements SchedulingConfigurer {
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(10);
        taskScheduler.initialize();
        taskRegistrar.setTaskScheduler(taskScheduler);

        taskRegistrar.addFixedRateTask(() -> System.out.println("执行定时任务1: " + new Date()), 1000);
        TriggerTask triggrtTask = new TriggerTask( // 任务内容.拉姆达表达式
                () -> {System.out.println("执行定时任务2: " + new Date());},
                // 设置触发器，这里是一个拉姆达表达式，传入的TriggerContext类型，返回的是Date类型
                triggerContext -> {
                    // 2.3 返回执行周期(Date)
                    return new CronTrigger("0 15 10 17 11 1 2019").nextExecutionTime(triggerContext);
                });

        taskRegistrar.addTriggerTask(triggrtTask);
    }

}
