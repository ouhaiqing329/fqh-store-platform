package com.fqh.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 应用程序
 *
 * @author fqh
 * @date 2022/08/11
 */
@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.info("==================store-platform run success！======================");
    }

}
