package com.fqh.auth.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户表
 */
@Data
@TableName(value = "ac_user")
public class AcUser implements Serializable {

    /**
     *
     */
    private String id;
    /**
     * 账号名
     */
    private String username;
    /**
     * hash存储
     */
    private String password;
    /**
     * 性别[0-不详 1-男  2-女]
     */
    private Integer sex;
    /**
     * 出生日期
     */
    private LocalDateTime birthday;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 头像
     */
    private String headPortrait;
    /**
     * 认证信息[对应认证信息表Id,扩展使用]
     */
    private String authInformationId;
    /**
     * 详细信息【扩展使用】
     */
    private String detailInformationId;
    /**
     * 最后登录时间
     */
    private LocalDateTime lastLoginTime;
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
