package com.fqh.storeserver;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ouhaiqing
 * @date 2022/8/12 18:28
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableEncryptableProperties
@Slf4j
@MapperScan(basePackages = "com.fqh.**.mapper")
public class StoreServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(StoreServerApplication.class,args);
        log.info("==================StoreServer服务启动成功！======================");
    }
}
