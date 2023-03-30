package com.fqh.auth.filter;


import com.fqh.auth.utils.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Configuration
@Slf4j
public class JwtTokenFilter implements OncePerRequestFilter {

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
        String token = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(token) && token.startsWith(HEADER_PREFIX)) {
            token = token.substring(7);
        }
        //判断token是否有效
         if (StringUtils.hasText(token)) {
             if(this.jwtTokenProvider.verify(token)){
                Authentication authentication = this.jwtTokenProvider.getAuthentication(token);
                // 如果请求头中有token，则进行解析，并且设置认证信息
                SecurityContextHolder.getContext().setAuthentication(getAuthentication(tokenHeader));
                //请求继续
                filterChain.doFilter(request,response);
                return;
             }else{
                 //续签
                 
             }    
           
        }  
        response.sendError(HttpStatus.UNAUTHORIZED.value());
    }
}
