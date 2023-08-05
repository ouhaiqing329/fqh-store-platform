package com.fqh.auth.config;

import com.alibaba.fastjson.JSON;
import com.fqh.auth.filter.JwtTokenFilter;
import com.fqh.auth.utils.JwtTokenProvider;
import com.fqh.utils.enums.ResultEnum;
import com.fqh.utils.response.BaseResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

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
        web.ignoring().antMatchers("/webjars/**", "/static/**","swagger/**","/swagger-ui.html");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 白名单
        String[] urls = {
                "/auth/login",
                "/auth/logout"
        };

        http.csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                //放行指定接口
                .authorizeRequests()
                .antMatchers(urls).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(new JwtTokenFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
                //不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                //添加未认证处理器
                .authenticationEntryPoint(new AuthenticationEntryPoint() {
                    @Override
                    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
                        response.setCharacterEncoding("UTF-8");
                        BaseResponseResult<Object> result = BaseResponseResult.baseResult(ResultEnum.AUTH_FAILED, authException.getMessage(), null);
                        response.getOutputStream().write(JSON.toJSONString(result).getBytes(StandardCharsets.UTF_8));
                    }
                })
                //添加拒绝处理
                .accessDeniedHandler(new AccessDeniedHandler() {
                    @Override
                    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
                        response.setCharacterEncoding("UTF-8");
                        BaseResponseResult<Object> result = BaseResponseResult.baseResult(ResultEnum.NO_ACCESS, accessDeniedException.getMessage(), null);
                        response.getOutputStream().write(JSON.toJSONString(result).getBytes(StandardCharsets.UTF_8));
                    }
                })


        ;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
