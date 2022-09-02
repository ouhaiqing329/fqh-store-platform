package com.fqh.userserver.entity;

import cn.hutool.extra.spring.SpringUtil;
import com.fqh.userserver.service.ITask;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author ouhaiqing
 * @date 2022/9/2 14:40
 */
public class ScheduleTaskBean extends QuartzJobBean {
    /**
     * 执行
     *
     * @param jobExecutionContext 工作执行上下文
     * @throws JobExecutionException 作业执行异常
     */
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //获取定时任务配置信息
        ScheduleTaskConfig scheduleTaskConfig = (ScheduleTaskConfig) jobExecutionContext.getMergedJobDataMap().get(ScheduleTaskConfig.SCHEDULE_JOB_NAME);

        //获取任务类实现类Bean
        Object target = SpringUtil.getBean(scheduleTaskConfig.getBeanName());

        //判断是否实现任务类接口
        if (!(target instanceof ITask)){
            throw new RuntimeException(String.format("任务类%s未实现接口%s",scheduleTaskConfig.getBeanName(),"ITask"));
        }

        try {
            //执行任务
            Method method = target.getClass().getMethod("run", String.class);
            method.invoke(target,scheduleTaskConfig.getParams());
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }


    }
}
