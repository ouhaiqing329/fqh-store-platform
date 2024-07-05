package com.fqh.utils.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * Redis自动配置
 *
 * @author fqh
 * @date 2022/08/11
 */
@Configuration
public class RedisAutoConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private String port;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.database}")
    private String index;

    /**
     * 连接工厂
     */
    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory(){
        RedisStandaloneConfiguration standaloneConfig = new RedisStandaloneConfiguration();
        standaloneConfig.setHostName(host);
        standaloneConfig.setPort(Integer.parseInt(port));
        if (!password.isEmpty()){
            standaloneConfig.setPassword(password);
        }
        if (Integer.parseInt(index) != 0){
            standaloneConfig.setDatabase(Integer.parseInt(index));
        }
        return new LettuceConnectionFactory(standaloneConfig);

    }


    /**
     * Redis单节点
     *
     * @param lettuceConnectionFactory 生菜连接工厂
     * @return {@link RedisTemplate}<{@link String}, {@link Object}>
     */
    @Bean("redisTemplate")
    @Primary
    @ConditionalOnProperty(name = "spring.redis.host")
    public RedisTemplate<String,Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setValueSerializer(RedisSerializer.string());
        return redisTemplate;
    }

}
