package com.fqh.auth;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 身份验证服务器应用程序
 *
 * @author fqh
 * @date 2022/08/14
 */
@SpringBootApplication(exclude = {RedisAutoConfiguration.class})
@EnableDiscoveryClient
@EnableFeignClients
public class AuthServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class, args);

    }

}
