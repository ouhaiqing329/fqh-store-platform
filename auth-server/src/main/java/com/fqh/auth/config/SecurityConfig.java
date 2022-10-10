package com.fqh.auth.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * 安全配置
 *
 * @author fqh
 * @date 2022/08/14
 */
@EnableWebFluxSecurity
@Slf4j
public class SecurityConfig {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Value("${permitUri}")
    private String[] permitUri;


    /**
     * 授权
     */
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
        httpSecurity
                //禁用csrf
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                //禁用跨域
                .cors(ServerHttpSecurity.CorsSpec::disable)
                //启用httpBasic请求
                .httpBasic(httpBasicSpec -> httpBasicSpec.authenticationManager(authenticationManager))
                .exceptionHandling(exceptionHandlingSpec -> exceptionHandlingSpec.authenticationEntryPoint((exchange, ex) -> {
                    ServerHttpResponse response = exchange.getResponse();
                    response.setStatusCode(HttpStatus.UNAUTHORIZED);
                    response.getHeaders().add("Content-Type","application/json;charset=utf-8");

                    Map<String, String> map = new HashMap<>();
                    map.put("code", "000000");
                    map.put("message", "未授权禁止访问");
                    log.error("认证失败！exception:",ex);
                    return response.writeWith(Mono.just(response.bufferFactory().wrap(JSON.toJSONBytes(map))));
                }))
                //通过认证但无权限进行的操作
//                .exceptionHandling(exceptionHandlingSpec -> exceptionHandlingSpec.accessDeniedHandler())
                //未认证
//                .exceptionHandling(exceptionHandlingSpec ->
//                        exceptionHandlingSpec.authenticationEntryPoint())
                .authorizeExchange()
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                //放行登录登出
                .pathMatchers(permitUri).permitAll()
                //放行登录请求
                // 任何请求需要身份认证
                .pathMatchers("/**").authenticated();
        return httpSecurity.build();
    }


}
