package com.fqh.userserver.service;

/**
 * 定时任务配置
 *
 * @author ouhaiqing
 * @date 2022/9/1 13:46
 */
public interface ScheduleTaskConfigService {
    /**
     * 根据配置Id运行指定定时任务
     *
     * @param id id
     * @return {@link Boolean}
     */
    Boolean run(Long id);
}
