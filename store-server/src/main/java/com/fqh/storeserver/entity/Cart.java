package com.fqh.storeserver.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Cart {
    private String id;
    private String userId;
    private String productId;
    private Integer quantity;
    private Integer selected;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    // 非数据库字段
    private Product product;

    /**
     * 删除标识（正常-0|已删除-1）
     */
    @TableLogic
    private Integer deleteMark;
}