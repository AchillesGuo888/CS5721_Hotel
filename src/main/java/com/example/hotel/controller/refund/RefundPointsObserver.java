package com.example.hotel.controller.refund;

import com.example.hotel.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;

public class RefundPointsObserver extends RefundObserver {

    @Autowired
    private UserPointsService userPointsService; // 用于管理用户积分的服务

    @Override
    public void update(Order order) {
        if (order == null || order.getUserId() == null || order.getEarnedPoints() <= 0) {
            throw new IllegalArgumentException("Invalid order data for points rollback.");
        }

        // Execute points rollback logic
        System.out.println("Rolling back " + order.getEarnedPoints() + " points for user: " + order.getUserId());

        // Calling the database operation to roll back the points
        userPointsService.deductPoints(String.valueOf(order.getUserId()), order.getEarnedPoints());
    }
}