package com.fqh.auth.controller;

import com.fqh.auth.config.AuthenticationManager;
import com.fqh.auth.utils.JwtTokenProvider;
import com.fqh.utils.response.BaseResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.HashMap;

/**
 * @author ouhaiqing
 * @date 2022/8/25 17:26
 */
@RestController
@RequestMapping("/auth")
@Api(value = "认证管理",tags = "认证管理")
@Slf4j
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    @ApiOperation(value = "登录")
    public BaseResponseResult<Void> login(String username, String password){
        //校验账号密码
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Mono<Authentication> authenticate = authenticationManager.authenticate(token);
        try {
            Authentication block = authenticate.block();
            if (block == null){
                return BaseResponseResult.error();
            }
            return BaseResponseResult.success(jwtTokenProvider.createToken(block.getPrincipal().toString(), block ,new HashMap<>()));
        } catch (Exception e) {
            return BaseResponseResult.error();
        }

    }
}
