package com.example.hotel.interceptor;

import com.example.hotel.core.Request;
import com.example.hotel.core.Response;

public interface Interceptor {
  boolean preHandle(InterceptorContext context) throws Exception;
  void postHandle(InterceptorContext context) throws Exception;
  void afterCompletion(InterceptorContext context) throws Exception;
}
