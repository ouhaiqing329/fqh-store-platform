package com.fqh.storeserver.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class CartDTO {
    /**
     * 购物车项目
     */
    private List<CartItemDTO> cartItems;
    /**
     * 总金额
     */
    private BigDecimal totalAmount;
    /**
     * 总量
     */
    private Integer totalQuantity;
    /**
     * 选定项目
     */
    private Integer selectedItems;
}