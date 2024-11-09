package com.example.hotel.filter;

import com.example.hotel.util.JwtUtil;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtFilter extends OncePerRequestFilter {
  @Autowired
  private JwtUtil jwtUtil;
  private static final String SECRET_KEY = "2024_CS5721";

  // without Token url
  private final static String IGNORE_URL = "/user/withoutToken";
  private final static String SWAGGER_URL = "swagger";
  private final static String API_DOCS_URL = "api-docs";
  private final static String WEBJARS_URL = "webjars";

  private static final String AUTHORIZATION_HEADER = "Authorization";
  private static final String TOKEN_PREFIX = "Bearer ";


  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String requestURI = request.getRequestURI();
    if (requestURI.startsWith(IGNORE_URL)||requestURI.contains(SWAGGER_URL)||requestURI.contains(API_DOCS_URL)||requestURI.contains(WEBJARS_URL)){
      filterChain.doFilter(request, response);
      return;
    }

    // get Authorization from request header
    String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);

    // check Authorization exist，and has Token
    if (authorizationHeader == null || !authorizationHeader.startsWith(TOKEN_PREFIX)) {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); //
      response.getWriter().write("Token is missing or invalid"); //
      return;
    }
    String token = authorizationHeader.substring(7);
    if (token != null && jwtUtil.validateToken(token)!=null) {
      if (jwtUtil.isTokenBlacklisted(token)) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("Token is in blacklist"); //
        return;
      }
    }

    filterChain.doFilter(request, response);
  }

}

