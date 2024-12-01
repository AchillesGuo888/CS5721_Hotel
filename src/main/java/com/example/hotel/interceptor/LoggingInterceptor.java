package com.example.hotel.interceptor;


import com.example.hotel.entity.LogInfo;
import com.example.hotel.mapper.LogInfoMapper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoggingInterceptor implements HandlerInterceptor {

  @Autowired
  private LogInfoMapper logInfoMapper;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
      Object handler) {

    return true;
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) {
    int status = response.getStatus();
    String url = request.getRequestURL().toString();
    String method = request.getMethod();
    String params = request.getQueryString();
    // String userName = ((SessionUser)request.getSession().getAttribute("user")).getUserName();
    LogInfo logInfo = new LogInfo();
    logInfo.setMethod(method);
    //logInfo.setOperator(userName);
    logInfo.setParams(params);
    logInfo.setUrl(url);
    logInfoMapper.insertSelective(logInfo);
    System.out.println("Response Status: " + status);
  }
}

