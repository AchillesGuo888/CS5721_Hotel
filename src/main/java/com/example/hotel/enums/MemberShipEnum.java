package com.example.hotel.enums;


public enum MemberShipEnum implements BaseEnum<Byte> {
    /**
     *
     */
    COOPER("COOPER",(byte)0),
    SILVER("SILVER",(byte)1),
    GOLD("GOLD",(byte)2),
    DIAMOND("DIAMOND",(byte)3),
    ;

    private String desc;
    private Byte code;

    MemberShipEnum(String desc, Byte code){
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
