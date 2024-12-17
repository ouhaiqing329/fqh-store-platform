package com.fqh.auth.config.log;

import com.fqh.auth.config.annotations.WebLog;
import com.google.gson.Gson;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class WebLogAspect {

    private final static Logger logger = LoggerFactory.getLogger(WebLogAspect.class);
    private static final Gson gson = new Gson();

    @Pointcut("@annotation(webLog)")
    public void webLogPointcut(WebLog webLog) {
    }

    @Around("webLogPointcut(webLog)")
    public Object around(ProceedingJoinPoint joinPoint, WebLog webLog) throws Throwable {
        long startTime = System.currentTimeMillis();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String ip = getClientIpAddress(request);
//        String className = joinPoint.getSignature().getDeclaringTypeName();
//        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        // 打印请求信息
        logger.info("================================== 日志开始 ==============================");
        logger.info("URL: {}", url);
        logger.info("HTTP Method: {}", method);
        logger.info("IP: {}", ip);
//        logger.info("Class Method: {}.{}", className, methodName);
        logger.info("Description: {}", webLog.description());
        logger.info("Request Args: {}", gson.toJson(args));

        Object result = joinPoint.proceed();

        long endTime = System.currentTimeMillis();
        logger.info("Response Args: {}", gson.toJson(result));
        logger.info("Time-Consuming: {}ms", endTime - startTime);
        logger.info("================================== 日志结束 ==============================");
        return result;
    }

    private String getClientIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }
}