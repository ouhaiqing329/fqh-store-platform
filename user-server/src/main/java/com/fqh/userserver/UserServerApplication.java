package com.fqh.userserver;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ouhaiqing
 * @date 2022/8/12 18:30
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableEncryptableProperties
@Slf4j
@MapperScan(basePackages = "com.fqh.userserver.**.mapper")
public class UserServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServerApplication.class,args);
        log.info("===================UserServe启动成功！=================");
    }
}
