package com.example.hotel.dto;

import lombok.Data;

@Data
public class JsonResult {

  /**
   * status code，0：failed，1：success
   */
  private Integer code;

  /**
   * return message
   */
  private String msg;

  /**
   * return object
   */
  private Object result;

  /**
   * constructor without result
   *
   * @param code code
   * @param msg  message
   */
  public JsonResult(Integer code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  /**
   * constructor with result
   *
   * @param code   code
   * @param msg    message
   * @param result data
   */
  public JsonResult(Integer code, String msg, Object result) {
    this.code = code;
    this.msg = msg;
    this.result = result;
  }

  /**
   * return code and data
   *
   * @param code   code
   * @param result data
   */
  public JsonResult(Integer code, Object result) {
    this.code = code;
    this.result = result;
  }

  public static JsonResult error(String msg) {
    return new JsonResult(0, msg);
  }

  public static JsonResult error(String msg, Object data) {
    return new JsonResult(0, msg, data);
  }

  public static JsonResult success() {
    return new JsonResult(1, "操作成功");
  }

  public static JsonResult success(String msg) {
    return new JsonResult(1, msg);
  }

  public static JsonResult success(String msg, Object result) {
    return new JsonResult(1, msg, result);
  }
}
