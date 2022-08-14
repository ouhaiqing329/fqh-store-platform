package com.fqh.orderserver;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
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
@EnableEncryptableProperties
@Slf4j
public class OrderServerApplication {


    public static void main(String[] args) {
        SpringApplication.run(OrderServerApplication.class, args);
        log.info("==================OrderServer run success！======================");
    }

}
