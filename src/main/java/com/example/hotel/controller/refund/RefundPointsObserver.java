package com.example.hotel.controller.refund;

import com.example.hotel.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;

public class RefundPointsObserver extends RefundObserver {

    @Autowired
    private UserPointsService userPointsService; // Services for managing user points

    @Override
    public void update(Order order) {
        //Verify that the order data is valid
        if (order == null || order.getUserId() == null || order.getEarnedPoints() <= 0) {
            throw new IllegalArgumentException("Invalid order data for points rollback.");
        }

        // Print debugging information, record the points and user ID to be rolled back
        System.out.println("Rolling back " + order.getEarnedPoints() + " points for user: " + order.getUserId());

        // Calling the database operation to roll back the points
        userPointsService.deductPoints(String.valueOf(order.getUserId()), order.getEarnedPoints());
    }
}