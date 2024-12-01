package com.example.hotel.common.base;

import com.example.hotel.util.JSONUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool2.BaseObject;


@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult<T> extends BaseObject {

  private static final long serialVersionUID = -3251574595114086260L;


  /**
   * 错误编码
   */
  public Long code;
  /**
   * 错误描述
   */
  public String msg = "";

  /**
   * 数据对象
   */
  public T data;
  /**
   * 结果扩展参数
   */
  private Map<String, Object> extparams;


  public ResponseResult() {
  }


  public ResponseResult(Long code, String msg) {
    this();
    this.code = code;
    this.msg = msg;
  }

  public ResponseResult(ResponseCode responseCode) {
    this();
    this.code = responseCode.getCode();
    this.msg = responseCode.getDesc();
  }

  public ResponseResult(Long code, String msg, T data) {
    this.code = code;
    this.msg = msg;
    this.data = data;
  }

  public static <T> ResponseResult<T> ofSuccess() {
    return new ResponseResult<>(ResponseCode.success.getCode(), ResponseCode.success.getDesc());
  }

  public static <T> ResponseResult<T> ofSuccess(T data) {
    return new ResponseResult<>(ResponseCode.success.getCode(), ResponseCode.success.getDesc(),
        data);
  }

  public static <T> ResponseResult<T> ofError(Long code, String msg) {
    return new ResponseResult<>(code, msg);
  }

  public static <T> ResponseResult<T> ofError(Long code, String msg, T data) {
    return new ResponseResult<>(code, msg, data);
  }

  public static <T> ResponseResult<T> ofParamError() {
    return new ResponseResult<>(ResponseCode.param_error.getCode(),
        ResponseCode.param_error.getDesc());
  }

  public static <T> ResponseResult<T> ofSystemError(String msg) {
    return new ResponseResult<>(ResponseCode.server_err.getCode(), msg);
  }

  public static <T> ResponseResult<T> ofSystemError() {
    return new ResponseResult<>(ResponseCode.server_err.getCode(),
        ResponseCode.server_err.getDesc());
  }

  /**
   * 添加参数
   *
   * @param name
   * @param value
   * @return
   */
  public ResponseResult<T> addExtparam(String name, Object value) {
    if (StringUtils.isEmpty(name)) {
      return this;
    }
    initmap();
    this.extparams.put(name, value);
    return this;
  }

  private void initmap() {
    if (this.extparams == null) {
      this.extparams = new HashMap<>();
    }
  }

  /**
   * 添加信息到map中
   *
   * @param map
   * @return
   */
  public ResponseResult<T> addExtparam(Map<String, Object> map) {
    if (map == null || map.size() <= 0) {
      return this;
    }
    initmap();
    this.extparams.putAll(map);
    return this;
  }


  public String toJson() {
    try {
      return JSONUtil.OBJECT_MAPPER.writeValueAsString(this);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  public Long getCode() {
    return code;
  }

  public void setCode(Long code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public Map<String, Object> getExtparams() {
    return extparams;
  }

  public void setExtparams(Map<String, Object> extparams) {
    this.extparams = extparams;
  }
}
