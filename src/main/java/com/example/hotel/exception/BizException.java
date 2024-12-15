package com.example.hotel.exception;


import com.example.hotel.common.base.ResponseCode;

public class BizException extends Exception {

    private static final long serialVersionUID = -1666315682491154125L;

    private final ResponseCode code;
    private final String message;

    public BizException(ResponseCode code, String message) {
      super(message);
      this.code = code;
      this.message = message;
    }

    public BizException(ResponseCode code, String message, Exception e) {
      super(message, e);
      this.code = code;
      this.message = message;
    }

    public BizException(ResponseCode code) {
      super(code.getDesc());
      this.code = code;
      this.message = code.getDesc();
    }

    public BizException(String message) {
      super(message);
      this.code = null;
      this.message = message;
    }

    public ResponseCode getCode() {
      return code;
    }

    @Override
    public String getMessage() {
      return message;
    }
}
