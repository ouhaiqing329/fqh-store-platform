package com.fqh.storeserver.config;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final int code;
    private final String message;

    public BusinessException(ExceptionCode exceptionCode) {
        this.code = exceptionCode.getCode();
        this.message = exceptionCode.getMessage();
    }

    public BusinessException(ExceptionCode exceptionCode, String defineMsg) {
        this.code = exceptionCode.getCode();
        this.message = defineMsg;
    }

    public BusinessException(int code, String message) {
        this.code = code;
        this.message = message;
    }
}