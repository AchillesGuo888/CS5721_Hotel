package com.example.hotel.core;

import java.util.Map;
import lombok.Data;

@Data
public class Request {
  private String url;
  private Map<String, String> headers;
  private String body;

  public Request(String url, Map<String, String> headers, String body) {
    this.url = url;
    this.headers = headers;
    this.body = body;
  }

}
