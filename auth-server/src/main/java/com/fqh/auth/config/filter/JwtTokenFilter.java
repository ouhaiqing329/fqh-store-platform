package com.fqh.auth.config.filter;


import com.alibaba.fastjson.JSON;
import com.fqh.auth.config.handle.ServiceException;
import com.fqh.auth.utils.JwtTokenProvider;
import com.fqh.auth.utils.RedisUtil;
import com.fqh.utils.response.BaseResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;


/**
 * jwt过滤器
 * 如果需要加入SpringSecurity，就不能交给SpringIOC容器进行管理
 *
 * @author fqh
 * @date 2023/05/11
 */
@Slf4j
@Order(1)
public class JwtTokenFilter extends OncePerRequestFilter {

    public static final String HEADER_PREFIX = "Bearer ";

    private final JwtTokenProvider jwtTokenProvider;


    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    /**
     * 过滤器（拦截请求头携带token的请求）
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        //从cookie中获取
        if (!StringUtils.hasText(token)){
            Cookie[] cookies = request.getCookies();
            if (Objects.nonNull(cookies) && cookies.length > 0){
                for (Cookie cookie : cookies) {
                    if (HttpHeaders.AUTHORIZATION.equals(cookie.getName())){
                        token = cookie.getValue();
                    }
                }
            }
        }
        if (StringUtils.hasText(token) && token.startsWith(HEADER_PREFIX)) {
            token = token.substring(7);
        }
        //判断token是否有效
        try {
            if (StringUtils.hasText(token)) {
                //判断token是否有效
                if (this.jwtTokenProvider.verify(token)) {
                    Authentication authentication = this.jwtTokenProvider.getAuthentication(token);
                    //查看该token是否存在白名单
                    if (!token.equals(RedisUtil.getJwtTokenCache(authentication.getPrincipal().toString()))) {
                        throw new ServiceException("jwt token hasFailure");
                    }
                    // 如果请求头中有token，则进行解析，并且设置认证信息
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    //请求继续
                    filterChain.doFilter(request, response);
                    return;
                }
            }
        } catch (Exception e) {
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(JSON.toJSONString(BaseResponseResult.error("用户未登录，请登录！")).getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
            return;
        }
        response.sendError(HttpStatus.UNAUTHORIZED.value());
    }
}
