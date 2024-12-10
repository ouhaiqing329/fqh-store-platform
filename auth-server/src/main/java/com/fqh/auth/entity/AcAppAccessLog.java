package com.fqh.auth.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户操作记录表
 * ac_app_access_log
 */
@Data
@TableName(value = "ac_app_access_log")
public class AcAppAccessLog implements Serializable {

    /**
     *
     */
    private String id;
    /**
     * 访问的url
     */
    private String targetUrl;
    /**
     * get和post参数
     */
    private String queryParams;
    /**
     * 访问ua
     */
    private String ua;
    /**
     * 访问ip
     */
    private String ip;
    /**
     * json格式备注字段
     */
    private String note;
    /**
     *
     */
    private LocalDateTime createdTime;

}
