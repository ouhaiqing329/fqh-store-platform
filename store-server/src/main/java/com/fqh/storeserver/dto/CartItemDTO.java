package com.fqh.storeserver.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class CartItemDTO {
    private String productId;
    private String productName;
    private String productImage;
    private BigDecimal price;
    private Integer quantity;
    private Boolean selected;
    private BigDecimal totalPrice;
}