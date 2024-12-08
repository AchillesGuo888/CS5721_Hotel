package com.example.hotel.filter;

import com.example.hotel.web.wrapper.CachedBodyHttpServletRequest;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class RequestBodyCachingFilter implements Filter {
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    if (request instanceof HttpServletRequest) {
      HttpServletRequest httpServletRequest = (HttpServletRequest) request;
      CachedBodyHttpServletRequest cachedRequest = new CachedBodyHttpServletRequest(httpServletRequest);
      chain.doFilter(cachedRequest, response);
    } else {
      chain.doFilter(request, response);
    }
  }
}
