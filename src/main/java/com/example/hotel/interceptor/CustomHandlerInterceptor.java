package com.example.hotel.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class CustomHandlerInterceptor implements HandlerInterceptor {
  private Dispatcher dispatcher;
  private LoggingInterceptor loggingInterceptor;

  public CustomHandlerInterceptor(Dispatcher dispatcher,
      LoggingInterceptor loggingInterceptor) {
    this.dispatcher = dispatcher;
    this.loggingInterceptor = loggingInterceptor;
    dispatcher.addInterceptor(loggingInterceptor);
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    return dispatcher.dispatch(request, handler);
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

  }
}
