package com.fqh.storeserver.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 购物车实体
 *
 * @author fqh
 * @date 2022/12/23
 */
@Data
public class CartEntity {

    /**
     * 购物车Id
     */
    private Long cartid;


    /**
     * 上一个购物车id
     */
    private Long lastCartid;

    /**
     * 商品列表
     *
     *
     */
    private String productIds;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;



}
