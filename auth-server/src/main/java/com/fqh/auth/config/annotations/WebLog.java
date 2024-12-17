package com.fqh.auth.config.annotations;

import java.lang.annotation.*;
 
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface WebLog {
    String description() default "";
}