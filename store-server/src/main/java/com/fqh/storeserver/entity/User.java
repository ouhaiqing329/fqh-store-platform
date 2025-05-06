package com.fqh.storeserver.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private String id;
    private String username;
    private String password;
    private String phone;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    /**
     * 删除标识（正常-0|已删除-1）
     */
    @TableLogic
    private Integer deleteMark;
}