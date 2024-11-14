package com.example.hotel.config;

import com.example.hotel.interceptor.AuthenticationInterceptor;
import com.example.hotel.interceptor.AuthorizationInterceptor;
import com.example.hotel.interceptor.LoggingInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class WebConfig implements WebMvcConfigurer {
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    // 添加身份验证拦截器
    registry.addInterceptor(new AuthenticationInterceptor()).addPathPatterns("/order/**", "/user/withToken/**", "/bill/**");

    // 添加权限控制拦截器
    registry.addInterceptor(new AuthorizationInterceptor()).addPathPatterns("/**/admin/**");

    // 添加日志记录拦截器
    registry.addInterceptor(new LoggingInterceptor()).addPathPatterns("/**");

  }
}
