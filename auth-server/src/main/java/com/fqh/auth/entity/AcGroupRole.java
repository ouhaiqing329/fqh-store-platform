package com.fqh.auth.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 角色/角色组表
 * ac_group_role
 */
@Data
@TableName(value = "ac_group_role")
public class AcGroupRole implements Serializable {

    /**
     *
     */
    private String id;
    /**
     * 上级Id
     */
    private String parentId;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 权限编码
     */
    private String code;
    /**
     * 角色类型 1-角色组|0-角色
     */
    private Integer type;
    /**
     * 是否禁用 1-启用 0-禁用
     */
    private Integer enabled;
    /**
     * 更新时间
     */
    private LocalDateTime modifyTime;
    /**
     * 插入时间
     */
    private LocalDateTime createdTime;
    /**
     * 创建人
     */
    private String creator;
    /**
     * 更新人
     */
    private String modifier;
    /**
     * 删除标识 1-已删除 | 2-正常
     */
    private Integer deleteMark;

}
