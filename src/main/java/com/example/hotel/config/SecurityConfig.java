package com.example.hotel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
        .authorizeRequests()
        .antMatchers("/swagger-ui.html", "/v2/api-docs", "/swagger-resources/**", "/webjars/**")
        .permitAll() // 允许访问 Swagger 相关的路径
        .antMatchers("/authenticate").permitAll() // 允许访问认证接口
        .anyRequest().authenticated() // 其他请求需要认证
        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

  }
}