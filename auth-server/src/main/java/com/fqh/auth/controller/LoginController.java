package com.fqh.auth.controller;

import com.fqh.auth.config.DefineAuthenticationManager;
import com.fqh.auth.filter.JwtTokenFilter;
import com.fqh.auth.utils.JwtTokenProvider;
import com.fqh.auth.utils.RedisUtil;
import com.fqh.utils.response.BaseResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ouhaiqing
 * @date 2022/8/25 17:26
 */
@RestController
@Api(value = "认证管理", tags = "认证管理")
@Slf4j
@RequestMapping("/sso")
public class LoginController {

    @Autowired
    private DefineAuthenticationManager defineAuthenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    @ApiOperation(value = "登录")
    public BaseResponseResult<String> login(String username, String password) {
        //校验账号密码
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authenticate = defineAuthenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        //生成Token
        String token = jwtTokenProvider.createToken(authenticate.getPrincipal().toString());
        //生成jwt白名单
        RedisUtil.setJwtTokenCache(username, token);
        return BaseResponseResult.success("登录成功",token);
    }

    @PostMapping("/logout")
    @ApiOperation(value = "注销")
    public BaseResponseResult<Void> logout(HttpServletRequest request) {
        //获取token
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(token) && token.startsWith(JwtTokenFilter.HEADER_PREFIX)) {
            token = token.substring(7);
        }
        //清除session
        try {
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            //将token 白名单清空
            RedisUtil.setJwtTokenCache(authentication.getPrincipal().toString(), "");
        } catch (Exception e) {
            log.error("logout failure! exception:", e);
        }

        return BaseResponseResult.success();
    }

    @GetMapping("/checkLogin")
    @ApiOperation(value = "检查登录")
    public BaseResponseResult<String> checkLogin(HttpServletRequest request) {
        //检查是否即将过期
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(token) && token.startsWith(JwtTokenFilter.HEADER_PREFIX)) {
            token = token.substring(7);
        }
        //自动续签
        String newToken = jwtTokenProvider.automaticRenewal(token);
        if (StringUtils.hasText(newToken)) {
            Authentication authentication = jwtTokenProvider.getAuthentication(newToken);
            //生成jwt白名单
            RedisUtil.setJwtTokenCache(authentication.getPrincipal().toString(), newToken);
        }
        return BaseResponseResult.success(newToken);
    }


}
