package com.hit.store.config;

import com.hit.store.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

// 将自定义拦截器注册到项目中 并添加黑名单和白名单
@Configuration
public class LoginInterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LoginInterceptor interceptor = new LoginInterceptor();
        // 配置白名单 存储在一个list列表中
        List<String> list = new ArrayList<>();
        list.add("/bootstrap3/**");
        list.add("/css/**");
        list.add("/images/**");
        list.add("/js/**");
        list.add("/web/index.html");
        list.add("/web/login.html");
        list.add("/web/register.html");
        list.add("/users/login");
        list.add("/users/reg");
        list.add("/products/**");
        list.add("/districts/**");
        registry.addInterceptor(interceptor).addPathPatterns("/**").excludePathPatterns(list);
    }
}
