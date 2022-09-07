package com.fqh.userserver.service.impl;

import com.fqh.userserver.entity.ScheduleTaskConfig;
import com.fqh.userserver.mapper.ScheduleTaskConfigMapper;
import com.fqh.userserver.service.ScheduleTaskConfigService;
import com.fqh.userserver.utils.ScheduleTaskUtil;
import com.fqh.utils.enums.ResultEnum;
import com.fqh.utils.handle.ServiceException;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

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
        return true;
    }

    /**
     * 运行
     */
    @Override
    public Boolean run(Long id) {
        //获取配置文件
        ScheduleTaskConfig scheduleTaskConfig = scheduleTaskConfigMapper.selectById(id);
        if (Objects.isNull(scheduleTaskConfig)){
            throw new ServiceException(ResultEnum.ERROR);
        }
        return ScheduleTaskUtil.run(scheduleTaskConfig);
    }
}
