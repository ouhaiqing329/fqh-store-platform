package com.fqh.auth.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户角色表
 */
@Data
@TableName(value = "ac_user_role")
public class AcUserRole implements Serializable {

    /**
     *
     */
    private String id;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 角色id
     */
    private String roleId;
    /**
     * 更新时间
     */
    private Date modifyTime;
    /**
     * 插入时间
     */
    private Date createdTime;
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
