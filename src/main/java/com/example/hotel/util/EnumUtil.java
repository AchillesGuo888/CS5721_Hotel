package com.example.hotel.util;


import com.example.hotel.enums.BaseEnum;

public class EnumUtil {

    public static <T extends BaseEnum> String getEnumDesc(byte status, Class<? extends BaseEnum> enumClass) {
        return getDesc(status,enumClass.getEnumConstants());
    }

    public static <T extends BaseEnum> String getDesc(byte status, T[] values) {
        String desc="";
        for (T item : values) {
            if (item.getCode().equals(status)) {
                desc= item.getDesc();
            }
        }
        return desc;
    }

}
