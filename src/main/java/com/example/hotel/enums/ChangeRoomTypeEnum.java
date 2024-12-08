package com.example.hotel.enums;

/**
 * <pre>
 *     change room type enum
 * </pre>
 */
public enum ChangeRoomTypeEnum implements BaseEnum<Byte> {

  UNCHANGED("UNCHANGED", (byte) 0),
  CHANGE("CHANGE", (byte) 1),
  UPDATE("UPDATE", (byte) 2),
  ;


  private String desc;
  private Byte code;

  ChangeRoomTypeEnum(String desc, Byte code) {
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
        return "CHANGE";
      case (byte) 2:
        return "UPDATE";
      default:
        return "UNCHANGED";
    }
  }


}
