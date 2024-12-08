package com.example.hotel.config;

import com.example.hotel.filter.RequestBodyCachingFilter;
import com.example.hotel.interceptor.CustomHandlerInterceptor;
import com.example.hotel.interceptor.Dispatcher;
import com.example.hotel.interceptor.LoggingInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Autowired
  private Dispatcher dispatcher;
  @Autowired
  private LoggingInterceptor loggingInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new CustomHandlerInterceptor(dispatcher,loggingInterceptor)).addPathPatterns("/**");
  }

  @Bean
  public Filter requestBodyCachingFilter() {
    return new RequestBodyCachingFilter();
  }
}