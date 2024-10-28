package com.example.hotel.enums;

/**
 * <pre>
 *     角色枚举
 * </pre>
 *
 */
public enum RoleEnum {

    /**
     * 管理员
     */
    ADMIN("admin",0),

    /**
     * 客户
     */
    CUSTOMER("customer", 1),

    /**
     * 工作人员
     */
    WORKER("worker", 2);

    private String value;

    RoleEnum(String value, int i) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
