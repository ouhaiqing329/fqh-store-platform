package com.fqh.auth.config;

import com.fqh.auth.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
public class DefineAuthenticationManager implements AuthenticationManager {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    /**
     * 进行身份验证
     *
     * @param authentication 身份验证
     * @return {@link Mono}<{@link Authentication}>
     */
    @Override
    public Authentication authenticate(Authentication authentication) {
        // 获取用户输入的用户名和密码
        final String username = authentication.getName();
        final String password = authentication.getCredentials().toString();

        // 进行密码的比对
        if (username.isEmpty() || password.isEmpty()){
            throw new UsernameNotFoundException("请输入账号密码");
        }
        // 获取封装用户信息的对象
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        // 密码校验--开启并行线程
       if (password.equals(userDetails.getPassword())){
           throw new UsernameNotFoundException("账号密码错误！");
       }
        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(),userDetails.getPassword());
    }
}
