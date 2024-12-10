package com.fqh.auth.common.enums;

import lombok.Getter;

@Getter
public enum ResourceTypeEnum {
    menu(0, "菜单"),
    FUNCTION(1, "功能");


    private final int code;
    private final String value;

    ResourceTypeEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }
}
