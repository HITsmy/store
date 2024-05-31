package com.hit.store.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

// 自定义拦截器 根据是否经过登录具有session中的一些键值对来过滤掉非法访问请求
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object obj = request.getSession().getAttribute("uid");
        if(obj == null) {
            // 重定向到登录界面
            response.sendRedirect("/web/login.html");
            return false;
        }
        return true;
    }
}
