package com.example.hotel.controller.refund;

import com.example.hotel.controller.refund.RefundObserver;
import com.example.hotel.entity.Order;
import com.example.hotel.service.oder.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

public class RefundOrderStatusObserver extends RefundObserver {

    @Autowired
    private OrderService orderService;
    @Override
    public void update(Order order) {
        if (order == null || order.getOrderId() == null) {
            throw new IllegalArgumentException("Invalid order data for status update.");
        }

        // Update order status to "Cancelled"
        System.out.println("Updating order status to 'Cancelled' for order: " + order.getOrderId());

        // Call the order service to update the order status in the database
        boolean isUpdated = orderService.updateOrderStatus(order.getOrderId(), "Cancelled");

        // Check update results
        if (isUpdated) {
            System.out.println("Order status successfully updated for order: " + order.getOrderId());
        } else {
            System.err.println("Failed to update order status for order: " + order.getOrderId());
        }
    }
}
