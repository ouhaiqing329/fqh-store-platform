package com.fqh.auth.config;

import com.alibaba.fastjson.JSON;
import com.fqh.auth.filter.JwtTokenFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.authentication.DelegatingReactiveAuthenticationManager;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
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
@EnableWebFluxSecurity
@Slf4j
public class SecurityConfig {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenFilter jwtTokenFilter;

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
                .addFilterBefore(jwtTokenFilter, SecurityWebFiltersOrder.HTTP_BASIC)
                //启用httpBasic请求
                .httpBasic(httpBasicSpec -> httpBasicSpec.authenticationManager(authenticationManager))
                //认证失败
                //用户没有认证授权便直接访问
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
                .pathMatchers("/swagger-ui.html","/webjars","swagger-resources","/*/v2/api-doce","/favicon.ico","/auth/login").permitAll()
                // 任何请求需要身份认证
                .pathMatchers("/**").authenticated();
        return httpSecurity.build();
    }


    /**
     * 注册用户信息验证管理器，可按需求添加多个按顺序执行
     */
//    @Bean
//    ReactiveAuthenticationManager reactiveAuthenticationManager() {
//        LinkedList<ReactiveAuthenticationManager> managers = new LinkedList<>();
//        managers.add(authentication -> {
//            // 其他登陆方式 (比如手机号验证码登陆) 可在此设置不得抛出异常或者 Mono.error
//            return Mono.empty();
//        });
//        // 必须放最后不然会优先使用用户名密码校验但是用户名密码不对时此 AuthenticationManager 会调用 Mono.error 造成后面的 AuthenticationManager 不生效
//        managers.add(new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsServiceImpl));
//        managers.add(tokenAuthenticationManager);
//        return new DelegatingReactiveAuthenticationManager(managers);
//    }


}
