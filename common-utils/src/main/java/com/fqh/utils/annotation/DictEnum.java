package com.fqh.utils.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字典枚举自动转换
 *
 * @author ouhaiqing
 * @date 2022/9/5 18:25
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DictEnum {

    Class<? extends Enum> enumType();
}
