package com.example.hotel.controller.refund;

import com.example.hotel.entity.Order;

public class RefundPointsObserver extends RefundObserver {

    @Override
    public void update(Order order) {
        // Execute points rollback logic
        System.out.println("Rolling back points for user: " + order.getUserId());
        // 这里可以调用回滚积分的数据库操作
        // rollbackUserPoints(order.getUserId(), order.getEarnedPoints());
    }
}