package com.fqh.utilities.enums;


/**
 * 响应结果枚举
 *
 * @author fqh
 * @date 2022/08/14
 */
public enum ResultEnum {
    /**
     * 响应结果枚举
     **/
    SUCCESS(0, "success"),
    ERROR(1, "error"),
    ;


    public final int code;
    public final String desc;

    ResultEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }


}

