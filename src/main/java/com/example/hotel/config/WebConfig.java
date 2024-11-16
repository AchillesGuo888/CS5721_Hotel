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
    // Add an authentication interceptor
    registry.addInterceptor(new AuthenticationInterceptor()).addPathPatterns("/order/**", "/user/withToken/**", "/bill/**");

    // Add permission control interceptor
    registry.addInterceptor(new AuthorizationInterceptor()).addPathPatterns("/**/admin/**");

    // Add a logging interceptor
    registry.addInterceptor(new LoggingInterceptor()).addPathPatterns("/**");

  }
}
