package com.fqh.utilities.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


/**
 * 图像工具
 *
 * @author fqh
 * @date 2023/01/07
 */
public class ImageUtil {

    public static final Logger log = LoggerFactory.getLogger(ImageUtil.class);


    /**
     * 获取Servlet下目录
     *
     * @return {@link String}
     */
    public static String getServicePath(){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        return "http://" + request.getServerName() + ":" + request.getServerPort() + "/upload/";
    }

}
