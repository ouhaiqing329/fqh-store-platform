package com.fqh.userserver.service.impl;

import com.fqh.userserver.entity.ScheduleTaskConfig;
import com.fqh.userserver.mapper.ScheduleTaskConfigMapper;
import com.fqh.userserver.service.ScheduleTaskConfigService;
import com.fqh.userserver.utils.ScheduleTaskUtil;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 定时任务配置
 *
 * @author ouhaiqing
 * @date 2022/9/1 13:47
 */
@Service
public class ScheduleTaskConfigServiceImpl implements ScheduleTaskConfigService {

    @Autowired
    private ScheduleTaskConfigMapper scheduleTaskConfigMapper;

    @Autowired
    private Scheduler scheduler;

    /**
     * 保存
     */
    @Override
    public Boolean save(ScheduleTaskConfig scheduleTaskConfig) {
        Date createTime = new Date();
        scheduleTaskConfig.setCreateTime(createTime);
        scheduleTaskConfig.setUpdateTime(createTime);
        int row = scheduleTaskConfigMapper.insert(scheduleTaskConfig);
        if (row > 0) {
            ScheduleTaskUtil.createScheduleJob(scheduleTaskConfig);
        }
        return null;
    }

    @Override
    public Boolean run(Long id) {
        ScheduleTaskConfig scheduleTaskConfig = scheduleTaskConfigMapper.selectById(id);
//        scheduler.triggerJob();
        return null;
    }
}
