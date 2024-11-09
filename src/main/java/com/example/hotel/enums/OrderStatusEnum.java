package com.example.hotel.enums;

/**
 * <pre>
 *     order enum
 * </pre>
 */
public enum OrderStatusEnum implements BaseEnum<Byte>{

    NOT_PAY("NOT_PAY",(byte)0),
    HAS_PAY("HAS_PAY",(byte)1),
    FINISHED("FINISHED",(byte)2),
    ;



    private String desc;
    private Byte code;

    OrderStatusEnum(String desc, Byte code){
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
