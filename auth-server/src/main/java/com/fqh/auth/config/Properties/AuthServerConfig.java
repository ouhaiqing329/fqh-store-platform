package com.fqh.auth.config.Properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Feign客户端配置
 *
 * @author fqh
 * @date 2022/08/14
 */
@ConfigurationProperties(prefix = "auth.server.config")
@Configuration
public class AuthServerConfig {

    private UserServer userServer;

}

class UserServer{

    private String domain;
    /**
     * 根据用户名获取用户信息
     */
    private String getUserInfo;

}