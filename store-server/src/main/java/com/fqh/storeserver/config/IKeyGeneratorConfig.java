package com.fqh.storeserver.config;

import com.baomidou.mybatisplus.core.incrementer.IKeyGenerator;
import com.baomidou.mybatisplus.extension.incrementer.H2KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ouhaiqing
 * @date 2022/8/23 10:37
 */
@Configuration
public class IKeyGeneratorConfig {

    /**
     * 使用内置主键生成器
     *
     * @return {@link IKeyGenerator}
     */
    @Bean
    public IKeyGenerator keyGenerator() {
        return new H2KeyGenerator();
    }

}
