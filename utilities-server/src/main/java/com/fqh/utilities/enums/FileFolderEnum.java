package com.fqh.utilities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FileFolderEnum {
    COMMON("common", "通用文件");

    private final String code;
    private final String desc;
}
