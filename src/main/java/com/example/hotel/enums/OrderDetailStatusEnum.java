package com.example.hotel.enums;

/**
 * <pre>
 *     change room type enum
 * </pre>
 */
public enum OrderDetailStatusEnum implements BaseEnum<Byte> {

  BOOKED("BOOKED", (byte) 0),
  CANCELED("CANCELED", (byte) 1),
  CHANGED("CHANGED", (byte) 2),
  ;


  private String desc;
  private Byte code;

  OrderDetailStatusEnum(String desc, Byte code) {
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
      case (byte) 1:
        return "CANCELED";
      case (byte) 2:
        return "CHANGED";
      default:
        return "BOOKED";
    }
  }


}
