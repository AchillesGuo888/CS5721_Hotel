package com.example.hotel.exception;


import com.example.hotel.common.base.ResponseCode;

public class BizException extends Exception {

  private static final long serialVersionUID = -1666315682491154125L;
  private ResponseCode code;

  public BizException(ResponseCode code, String message) {
    super(message);
    this.code = code;
  }

  public BizException(ResponseCode code, String message, Exception e) {
    super(message, e);
    this.code = code;
  }

  public BizException(ResponseCode code) {
    super(code.getDesc());
    this.code = code;
  }

  public ResponseCode getCode() {
    return code;
  }
}
