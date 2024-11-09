package com.example.hotel.config;

import com.example.hotel.filter.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
        .authorizeRequests()
        .antMatchers("/swagger-ui.html", "/v2/api-docs", "/swagger-resources/**", "/webjars/**", "/user/withoutToken/**")
        .permitAll() // allow Swagger url
        .anyRequest().authenticated() // other request
        .and()
//        .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class) // 注册 JwtFilter
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //TODO test step don't need AuthenticationFilter
  }

//  @Bean
//  public JwtFilter jwtFilter() {
//    return new JwtFilter(); // create JwtFilter instance
//  }
}