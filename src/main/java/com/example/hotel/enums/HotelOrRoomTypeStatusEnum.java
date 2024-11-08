package com.example.hotel.enums;

/**
 * <pre>
 *     HotelOrRoomTypeStatusEnum
 * </pre>
 */
public enum HotelOrRoomTypeStatusEnum implements BaseEnum<Byte>{

    AVAILABLE("AVAILABLE",(byte)0),
    FULL("FULL",(byte)1),

    ;



    private String desc;
    private Byte code;

    HotelOrRoomTypeStatusEnum(String desc, Byte code){
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


}
