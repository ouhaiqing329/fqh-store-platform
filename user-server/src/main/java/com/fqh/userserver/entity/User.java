package com.fqh.userserver.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.Id;

/**
    * 用户基本信息
    */
@Data
@TableName(value = "t_user")
public class User {
    /**
    * 用户Id
    */
    @Id
    private Long id;

    /**
    * 用户账号
    */
    private String username;

    /**
    * 密码
    */
    private String password;

    /**
    * 性别[0-不详 1-男  2-女]
    */
    private Integer sex;

    /**
    * 出生日期
    */
    private Date birthday;

    /**
    * 昵称
    */
    private String nickName;

    /**
    * 头像
    */
    private String headPortrait;

    /**
    * 个人简介
    */
    private String introduction;

    /**
    * 认证信息[对应认证信息表Id,扩展使用]
    */
    private Long authInformationId;

    /**
    * 详细信息[扩展使用]
    */
    private Long detailInformationId;

    /**
    * 最后登录时间[冗余字段，扩展使用]
    */
    private Date lastLoginTime;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
    * 删除标识[0-正常 1-已删除]
    */
    @TableLogic
    private Integer deleteMark;
}