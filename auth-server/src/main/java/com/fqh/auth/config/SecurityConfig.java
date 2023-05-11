package com.fqh.auth.config;

import com.alibaba.fastjson.JSON;
import com.fqh.auth.filter.JwtTokenFilter;
import com.fqh.auth.service.impl.UserDetailsServiceImpl;
import com.fqh.auth.utils.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DelegatingReactiveAuthenticationManager;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 安全配置
 *
 * @author fqh
 * @date 2022/08/14
 */
@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public void configure(WebSecurity web) throws Exception {
        //放行静态资源
        web.ignoring().antMatchers("/webjars/**","/static/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 白名单
        String[] urls = {
                "/login",
                "/logout"
        };

        http.csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .addFilterBefore(new JwtTokenFilter(jwtTokenProvider,urls), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(urls)
                .permitAll()
                .anyRequest()
                .authenticated()
        ;
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
