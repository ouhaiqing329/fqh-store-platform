package com.fqh.storeserver.config;

import lombok.Getter;

@Getter
public enum ExceptionCode {
    PRODUCT_NOT_EXIST(1001, "商品不存在或已下架"),
    PRODUCT_STOCK_NOT_ENOUGH(1002, "商品库存不足"),
    CART_ITEM_NOT_EXIST(1003, "购物车商品不存在"),
    INVALID_PARAMETER(1004, "参数错误");

    private final int code;
    private final String message;

    ExceptionCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}