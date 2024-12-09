package com.example.hotel.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.Data;

@Data
public class InterceptorContext {
  private HttpServletRequest request;
  private Object response;
  private Object handler;
  private Exception exception;


  public InterceptorContext(HttpServletRequest request, Object handler,
      HttpServletResponse response, Exception exception) {
    this.request = request;
    this.handler = handler;
    this.response = response;
    this.exception = exception;
  }


}
