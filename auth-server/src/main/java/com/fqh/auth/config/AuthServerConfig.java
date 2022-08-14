package com.fqh.auth.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Feign客户端配置
 *
 * @author fqh
 * @date 2022/08/14
 */
@ConfigurationProperties(prefix = "auth.server.config")
@Component
public class AuthServerConfig {

    private UserServer userServer;

}

class UserServer{
    /**
     * 根据用户名获取用户信息
     */
    private String getUserInfo;

}