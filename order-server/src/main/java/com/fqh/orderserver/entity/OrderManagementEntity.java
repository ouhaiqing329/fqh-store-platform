package com.fqh.orderserver.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 订单管理实体
 *
 * @author ouhaiqing
 * @date 2022/9/7 11:06
 */
@Data
@TableName(value = "order_management")
public class OrderManagementEntity {

    /**
     * 订单编号
     */
    @TableId
    private Long id;

    /**
     * 总价
     */
    private Double totalPrice;

    /**
     * 运费
     */
    private Double freight;

    /**
     * 实付款
     */
    private Double actualPayment;

    /**
     * 订单状态（已下单-1|未支付-2|已支付-3|取消订单-4|拒绝订单-5|已发货-6|配送中-7|派送中-8|已收货-9|已结束-10|
     * 申请售后-11|拒绝售后-12|客服介入-13|售后结束-14|取消售后-15）
     */
    private Integer status;

    /**
     * 商品信息信息。json格式持久化
     */
    private String productInfo;

    /**
     * 支付类型
     */
    private String payType;

    /**
     * 支付凭证
     */
    private String payOrder;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 支付时间
     */
    private Date payTime;

    /**
     * 发货时间
     */
    private Date deliveryTime;

    /**
     * 创建用户
     */
    private Long createUser;


    /**
     * 删除标识
     */
    @TableLogic
    private Integer deleteMark;
}
