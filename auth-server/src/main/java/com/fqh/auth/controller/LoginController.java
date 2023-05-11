package com.fqh.auth.controller;

import com.fqh.auth.config.DefineAuthenticationManager;
import com.fqh.auth.utils.JwtTokenProvider;
import com.fqh.utils.response.BaseResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private DefineAuthenticationManager defineAuthenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    @ApiOperation(value = "登录")
    public BaseResponseResult<Void> login(String username, String password){
        //校验账号密码
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authenticate = defineAuthenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        //生成Token
        return BaseResponseResult.success(jwtTokenProvider.createToken(authenticate.getPrincipal().toString()));
    }
}
