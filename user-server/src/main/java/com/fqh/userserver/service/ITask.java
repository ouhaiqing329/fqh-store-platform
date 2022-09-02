package com.fqh.userserver.service;

/**
 * 任务类父接口
 *
 * @author ouhaiqing
 * @date 2022/9/2 16:04
 */
public interface ITask {

    /**
     * 运行
     *
     * @param params 参数
     */
    void run(String params);
}
