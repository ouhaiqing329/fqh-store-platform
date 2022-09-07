package com.fqh.storeserver.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.util.Date;

/**
 * 商品实体
 *
 * @author ouhaiqing
 * @date 2022/9/7 10:01
 */
@Data
public class ProductEntity {

    @TableId
    private Long id;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 库存
     */
    private Long inventory;

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
     * 商品状态（启用-1|停用-0）
     */
    private Integer status;

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
     * 删除标识（正常-0|已删除-1）
     */
    @TableLogic
    private Integer deleteMark;


}
