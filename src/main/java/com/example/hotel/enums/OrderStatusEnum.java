package com.example.hotel.enums;

/**
 * <pre>
 *     订单状态enum
 * </pre>
 */
public enum OrderStatusEnum {

    /**
     * whait to pay
     */
    NOT_PAY(0),

    /**
     * paid
     */
    HAS_PAY(1),

    /**
     * finished the order
     */
    FINISHED(2),

    /**
     * cancelled
     */
    CANCELLED(3);

    private Integer code;

    OrderStatusEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

}
