package com.example.hotel.config;

import com.example.hotel.filter.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
        .authorizeRequests()
        .antMatchers("/swagger-ui.html", "/v2/api-docs", "/swagger-resources/**", "/webjars/**")
        .permitAll() // allow Swagger url
        .anyRequest().authenticated() // other request
        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

  }

  @Bean
  public JwtFilter jwtFilter() {
    return new JwtFilter(); // create JwtFilter instance
  }
}