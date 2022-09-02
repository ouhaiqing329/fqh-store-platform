package com.fqh.userserver.service.impl;

import com.fqh.userserver.service.ITask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 用户状态停用任务
 *
 * @author ouhaiqing
 * @date 2022/9/2 16:05
 */
@Component("UserStatusStopTask")
@Slf4j
public class UserStatusStopTask implements ITask {

    /**
     * 运行
     *
     * @param params 参数个数
     */
    @Override
    public void run(String params) {

    }
}
