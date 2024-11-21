package com.example.hotel.service.oder;

import com.example.hotel.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    /**
     * Update order status
     * @param orderId
     * @param status New order status
     * @return Number of rows affected
     */
    public boolean updateOrderStatus(Long orderId, String status) {
        if (orderId == null || status == null || status.isEmpty()) {
            throw new IllegalArgumentException("Invalid order ID or status.");
        }

        int rowsAffected = orderMapper.updateOrderStatus(orderId, status);
        return rowsAffected > 0; // Returns whether the record has been updated
    }
}