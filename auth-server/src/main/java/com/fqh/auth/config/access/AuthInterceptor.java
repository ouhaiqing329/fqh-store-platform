package com.fqh.auth.config.access;

import com.fqh.auth.config.annotations.RequirePermissions;
import com.fqh.auth.config.handle.ServiceException;
import com.fqh.auth.service.access.AcResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 身份验证拦截器
 *
 * @author ouhaiqing
 * @date 2024/12/10
 */
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private final AcResourceService acResourceService;

    /**
     * controller接口权限拦截
     *
     * @param request  请求
     * @param response 响应
     * @param handler  处理程序
     * @return boolean
     * @throws Exception 异常
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //获取用户登录信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null){
            throw new ServiceException("未找到用户信息！");
        }

        //判断是否需要权限控制
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        RequirePermissions requirePermissions = method.getAnnotation(RequirePermissions.class);
        if (requirePermissions != null){
            //获取用户权限
//            acResourceService.getFunctionCodeList(authentication.getPrincipal().toString());
        }

        return true;
    }
}
