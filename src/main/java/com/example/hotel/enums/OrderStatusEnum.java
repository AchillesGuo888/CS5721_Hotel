package com.example.hotel.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * <pre>
 *     order enum
 * </pre>
 */
public enum OrderStatusEnum implements BaseEnum<Byte> {

  NOT_PAY("NOT_PAY", (byte) 0),
  HAS_PAY("HAS_PAY", (byte) 1),
  CHECK_IN("CHECK_IN", (byte) 2),
  FINISHED("FINISHED", (byte) 3),
  ;


  private String desc;
  private Byte code;

  OrderStatusEnum(String desc, Byte code) {
    this.desc = desc;
    this.code = code;
  }

  @Override
  public Byte getCode() {
    return this.code;
  }

  @Override
  public String getDesc() {
    return this.desc;
  }

  public static String getDescByCode(Byte code) {
    switch (code) {
      case (byte) 0:
        return "NOT_PAY";
      case (byte) 1:
        return "HAS_PAY";
      case (byte) 2:
        return "CHECK_IN";
      case (byte) 3:
        return "FINISHED";
    }
    return StringUtils.EMPTY;
  }
}
