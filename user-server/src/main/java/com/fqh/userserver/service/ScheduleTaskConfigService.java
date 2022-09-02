package com.fqh.userserver.service;

import com.fqh.userserver.entity.ScheduleTaskConfig;

/**
 * 定时任务配置
 *
 * @author ouhaiqing
 * @date 2022/9/1 13:46
 */
public interface ScheduleTaskConfigService {

    /**
     * 保存
     *
     * @param scheduleTaskConfig 定时任务配置
     * @return {@link Boolean}
     */
    Boolean save(ScheduleTaskConfig scheduleTaskConfig);
    /**
     * 根据配置Id运行指定定时任务
     *
     * @param id id
     * @return {@link Boolean}
     */
    Boolean run(Long id);

}
