package com.example.hotel.interceptor;

import com.alibaba.fastjson.JSON;
import com.example.hotel.core.Request;
import com.example.hotel.core.Response;
import com.example.hotel.entity.LogInfo;
import com.example.hotel.service.logInfo.LogInfoService;
import java.io.BufferedReader;
import java.io.IOException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@AllArgsConstructor
public class LoggingInterceptor implements Interceptor {

  private final LogInfoService logInfoService;

  @Override
  public boolean preHandle(InterceptorContext context) throws IOException {
    // get request info
    LogInfo log = new LogInfo();
    log.setUrl(context.getRequest().getRequestURL().toString());
    log.setHeader(JSON.toJSONString(context.getRequest().getHeader("Authorization")));
    StringBuilder json = new StringBuilder();
    BufferedReader reader = context.getRequest().getReader();
    String line;
    while ((line = reader.readLine()) != null) {
      json.append(line);
    }
    log.setBody(json.toString());
    // save log info
    logInfoService.save(log);
    return true;
  }

  @Override
  public void postHandle(InterceptorContext context) {

  }

  @Override
  public void afterCompletion(InterceptorContext context) {

  }
}

