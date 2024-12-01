package com.example.hotel.interceptor;

import com.example.hotel.dto.SessionUser;
import com.example.hotel.enums.UserTypeEnum;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthorizationInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    HttpSession session = request.getSession();
    SessionUser user = (SessionUser) session.getAttribute("user");
    if (user != null && UserTypeEnum.ADMIN.getCode().equals(user.getUserType())) {
      return true; // 拥有管理员权限，放行请求
    }
    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Unauthorized access");
    return false; // 拦截请求
  }
}

