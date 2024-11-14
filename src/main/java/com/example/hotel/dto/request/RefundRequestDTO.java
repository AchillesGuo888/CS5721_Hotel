package com.example.hotel.dto.request;

public class RefundRequestDTO {
    private static Long orderId;
    private static Double amount;
    private static Long userId;
    private static Integer earnedPoints;

    public static Long getOrderId() {
        return orderId;
    }
    public static Double getAmount() {
        return amount;
    }
    public static Long getUserId() {
        return userId;
    }
    public static Integer getEarnedPoints() {
        return earnedPoints;
    }
}

