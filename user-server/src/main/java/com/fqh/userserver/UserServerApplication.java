package com.fqh.userserver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ouhaiqing
 * @date 2022/8/12 18:30
 */
@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
public class UserServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServerApplication.class,args);
        log.info("===================UserServe启动成功！=================");
    }
}
