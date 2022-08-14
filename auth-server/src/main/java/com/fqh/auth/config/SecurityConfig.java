package com.fqh.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * 安全配置
 *
 * @author fqh
 * @date 2022/08/14
 */
@EnableWebFluxSecurity
public class SecurityConfig {

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 授权
     */
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
        httpSecurity
                .authorizeExchange()
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                //放行登录登出
                .pathMatchers("/static/**","/auth/login","/auth/logout").permitAll()
                // 任何请求需要身份认证
                .pathMatchers("/gateway/**").authenticated()
                .and()
                .formLogin()
                .authenticationManager(authenticationManager)
                .loginPage("/auth/login")

        ;
        return httpSecurity.build();
    }

}
