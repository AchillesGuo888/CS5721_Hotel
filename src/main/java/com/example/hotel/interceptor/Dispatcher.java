package com.example.hotel.interceptor;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class Dispatcher {

  private List<Interceptor> interceptors = new ArrayList<>();

  public void addInterceptor(Interceptor interceptor) {
    interceptors.add(interceptor);
  }

  public boolean dispatch(HttpServletRequest request, Object handler) throws Exception {
    InterceptorContext context = new InterceptorContext(request, handler);
    try {
      for (Interceptor interceptor : interceptors) {
        if (!interceptor.preHandle(context)) {
          return false;
        }
      }
      return true;
    } catch (Exception e) {
      for (Interceptor interceptor : interceptors) {
        context.setException(e);
        interceptor.afterCompletion(context);
      }
      throw e;
    }
  }
}
