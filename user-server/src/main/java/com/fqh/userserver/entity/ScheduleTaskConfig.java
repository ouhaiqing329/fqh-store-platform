package com.fqh.userserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.util.Date;

/**
 * 定时任务配置
 *
 * @author ouhaiqing
 * @date 2022/9/1 13:33
 */
@Data
public class ScheduleTaskConfig {

    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * bean名称
     */
    private String beanName;

    /**
     * Json格式参数
     */
    private String params;

    /**
     * cron表达式
     */
    private String cron;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建用户
     */
    private Long createUser;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新用户
     */
    private Long updateUser;

    /**
     * 删除标记
     */
    @TableLogic
    private Integer deleteMark;


}
