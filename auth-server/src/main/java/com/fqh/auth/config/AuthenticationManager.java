package com.fqh.auth.config;

import com.fqh.auth.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * 身份验证管理器
 *
 * @author fqh
 * @date 2022/08/14
 */
@Component
public class AuthenticationManager implements ReactiveAuthenticationManager {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    /**
     * 进行身份验证
     *
     * @param authentication 身份验证
     * @return {@link Mono}<{@link Authentication}>
     */
    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        if (authentication.isAuthenticated()){
            return Mono.just(authentication);
        }
        // 获取用户输入的用户名和密码
        final String username = authentication.getName();
        final String password = authentication.getCredentials().toString();

        // 进行密码的比对
        if (username.isEmpty() || password.isEmpty()){
            throw new UsernameNotFoundException("请输入账号密码");
        }
        // 获取封装用户信息的对象
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        boolean flag = password.equals(userDetails.getPassword());
        // 校验通过
        if (flag) {
            // 将权限信息也封装进去
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
            return Mono.just(token);
        }

        throw new AuthenticationException("用户密码错误") {
        };
    }
}
