package com.fqh.utils;

import org.springframework.beans.BeanUtils;

import java.util.function.Supplier;

/**
 * bean对象处理
 *
 * @author ouhaiqing
 * @date 2022/8/23 10:49
 */
public class BeanUtil {

    private BeanUtil() {
    }

    public static <T,S> T copyProperties(S source, Supplier<T> target){
        if (source == null){
            return null;
        }
        BeanUtils.copyProperties(source,target.get());
        return target.get();
    }
}
