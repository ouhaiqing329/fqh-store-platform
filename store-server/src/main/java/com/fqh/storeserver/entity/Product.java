package com.fqh.storeserver.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Product {
    /**
     * id
     */
    private String id;
    /**
     * 名字
     */
    private String name;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 库存
     */
    private Integer stock;
    /**
     * 图像url
     */
    private String imageUrl;
    /**
     * 状态
     */
    private Integer status;

    /**
     * 版本
     */
    private Long version;

    /**
     * 库存锁
     */
    private Integer optimisticLock;


    /**
     * 商品种类类型（多类型，“，”分隔）
     */
    private String kindType;


    /**
     * 商品形状类型（多类型，“，”分隔）
     */
    private String shapeType;


    /**
     * 商品颜色类型（多类型，“，”分隔）
     */
    private String colourType;


    /**
     * 商品尺寸类型（多类型，“，”分隔）
     */
    private String sizeType;


    /**
     * 其他信息
     */
    private String otherInfo;


    /**
     * 原价
     */
    private Double originalPrice;


    /**
     * 优惠价
     */
    private Double reducedPrice;


    /**
     * 可使用的优惠券（“,”分隔）
     */
    private String enableDiscountCoupon;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 删除标识（正常-0|已删除-1）
     */
    @TableLogic
    private Integer deleteMark;
}