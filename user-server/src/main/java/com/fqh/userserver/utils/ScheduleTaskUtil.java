package com.fqh.userserver.utils;

import com.fqh.userserver.entity.ScheduleTaskBean;
import com.fqh.userserver.entity.ScheduleTaskConfig;
import com.fqh.utils.TimeUtil;
import com.fqh.utils.enums.ResultEnum;
import com.fqh.utils.handle.ServiceException;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;


/**
 * 定时任务工具类
 *
 * @author ouhaiqing
 * @date 2022/9/2 14:32
 */
public class ScheduleTaskUtil {

    private static final String TASK_PREFIX = "TASK-";

    private static final Logger log = LoggerFactory.getLogger(ScheduleTaskUtil.class);

    private static Scheduler scheduler;

    static {
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();
        } catch (SchedulerException ex) {
            log.warn("定时任务调度器初始化失败！,exception:",ex);
            throw new ServiceException(ResultEnum.ERROR);
        }
    }

    private ScheduleTaskUtil() {
    }

    /**
     * 创建定时任务
     *
     * @param scheduleTaskConfig 定时任务配置
     */
    public static void createScheduleJob(ScheduleTaskConfig scheduleTaskConfig) {
        try {
            //构建Job信息
            JobDetail detail = JobBuilder.newJob(ScheduleTaskBean.class).withIdentity(getJobKey(scheduleTaskConfig.getId())).build();

            //构建cron表达式
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleTaskConfig.getCron()).withMisfireHandlingInstructionDoNothing();

            //构建触发器
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(getTriggerKey(scheduleTaskConfig.getId())).withSchedule(cronScheduleBuilder).build();

            //放入参数到jobDataMap -- 防止定时器立即执行 无值
            detail.getJobDataMap().put(ScheduleTaskConfig.SCHEDULE_JOB_NAME,scheduleTaskConfig);

            //绑定JobDetail和触发器
            Date date = scheduler.scheduleJob(detail, trigger);

            //根据状态控制定时器启用


            String currentTime = TimeUtil.dateToString(date);
            log.info("scheduleJob 初始化构建成功,jobKey:{},构建时间：{}",getJobKey(scheduleTaskConfig.getId()),currentTime);
        } catch (SchedulerException e) {
            log.warn("scheduleJob 初始化构建失败!jobKey:{}",getJobKey(scheduleTaskConfig.getId()));
        }
    }

    /**
     * 标识触发器
     *
     * @param id id
     * @return {@link TriggerKey}
     */
    private static TriggerKey getTriggerKey(Long id) {
        return new TriggerKey(TASK_PREFIX + id);
    }

    /**
     * 标识任务
     *
     * @param id id
     * @return {@link JobKey}
     */
    private static JobKey getJobKey(Long id) {
        return new JobKey(TASK_PREFIX + id);
    }

    /**
     * 运行
     *
     * @param scheduleTaskConfig 计划任务配置
     * @return {@link Boolean}
     */
    public static Boolean run(ScheduleTaskConfig scheduleTaskConfig) {

        try {
            JobDataMap data = new JobDataMap();
            scheduler.triggerJob(getJobKey(scheduleTaskConfig.getId()), data);
        } catch (SchedulerException ex) {
            log.warn("定时任务启动失败！id:{},exception:",scheduleTaskConfig.getId(),ex);
            return false;
        }
        return true;
    }
}
