package com.example.hotel.interceptor;

import com.example.hotel.dto.SessionUser;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthenticationInterceptor implements HandlerInterceptor {
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    HttpSession session = request.getSession();
    SessionUser user = (SessionUser) session.getAttribute("user");
    if (user == null) {
      response.sendRedirect("/login"); // 重定向到登录页面
      return false; // 拦截请求
    }
    return true; // 放行请求
  }
}

