package com.fqh.auth.filter;


import com.fqh.auth.utils.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * jwt过滤器
 * 如果需要加入SpringSecurity，就不能交给SpringIOC容器进行管理
 *
 * @author fqh
 * @date 2023/05/11
 */
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {

    public static final String HEADER_PREFIX = "Bearer ";

    private final JwtTokenProvider jwtTokenProvider;

    private String[] urls;


    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider, String[] urls) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.urls = urls;
    }

    /**
     * 过滤器（拦截请求头携带token的请求）
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //放行
        //请求路径
        String requestURI = request.getRequestURI();
        if (urls.length > 0 && StringUtils.hasText(requestURI)){
            for (int i = 0; i < urls.length; i++) {
                //判断白名单url是否有**符号
                if (urls[i].indexOf("**") > 0 ) {
                    //正则匹配
                    String regex = urls[i].replaceAll("\\*\\*", "([\\w\\/-]+)");
                    Pattern compile = Pattern.compile(regex);
                    Matcher matcher = compile.matcher(requestURI);
                    if (matcher.matches()){
                        //请求继续
                        filterChain.doFilter(request, response);
                        return;
                    }
                } else {
                    //直接匹配
                    if (requestURI.equals(urls[i])) {
                        //请求继续
                        filterChain.doFilter(request, response);
                        return;
                    }
                }

            }
        }
        response.setCharacterEncoding("UTF-8");
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(token) && token.startsWith(HEADER_PREFIX)) {
            token = token.substring(7);
        }
        //判断是否是第一次登录
         if (StringUtils.hasText(token)) {
             //判断token是否有效
             if(this.jwtTokenProvider.verify(token)){
                Authentication authentication = this.jwtTokenProvider.getAuthentication(token);
                // 如果请求头中有token，则进行解析，并且设置认证信息
                SecurityContextHolder.getContext().setAuthentication(authentication);
                //请求继续
                filterChain.doFilter(request,response);
                return;
             }
        }  
        response.sendError(HttpStatus.UNAUTHORIZED.value());
    }
}
