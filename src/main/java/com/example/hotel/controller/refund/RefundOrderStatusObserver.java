package com.example.hotel.controller.refund;

import com.example.hotel.controller.refund.RefundObserver;
import com.example.hotel.entity.Order;

public class RefundOrderStatusObserver extends RefundObserver {

    @Override
    public void update(Order order) {
        // Execute order status update logic
        System.out.println("Updating order status to 'Cancelled' for order: " + order.getOrderId());
        // 这里可以调用回滚积分的数据库操作
        //updateOrderStatus(order.getOrderId(), "Cancelled");
    }
}
