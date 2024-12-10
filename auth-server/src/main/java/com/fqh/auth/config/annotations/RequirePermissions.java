package com.fqh.auth.config.annotations;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RequirePermissions {

    /**
     * 权限编码
     *
     * @return {@link String }
     */
    String value() default "";
}
