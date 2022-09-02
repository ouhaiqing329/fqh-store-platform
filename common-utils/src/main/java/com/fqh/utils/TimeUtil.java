package com.fqh.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具
 *
 * @author ouhaiqing
 * @date 2022/9/2 15:05
 */
public final class TimeUtil {

    private static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";


    /**
     * 时间对象格式化为字符串
     *
     * @param date 日期
     * @return {@link String}
     */
    public static String dateToString(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATETIME_PATTERN);
        return dateFormat.format(date);
    }
}
