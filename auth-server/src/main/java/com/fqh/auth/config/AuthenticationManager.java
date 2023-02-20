package com.fqh.auth.config;

import com.fqh.auth.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Objects;


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
        Mono<UserDetails> detailsMono = userDetailsService.findByUsername(username);
        // 密码校验--开启并行线程
        return detailsMono
                .publishOn(Schedulers.single())
                .filter(u -> password.equals(u.getPassword()))
                .switchIfEmpty(Mono.defer(() -> Mono.error(new BadCredentialsException("账号密码错误"))))
                //转换为Authentication
                .map(u-> new UsernamePasswordAuthenticationToken(u.getUsername(), u.getPassword()));
    }
}
