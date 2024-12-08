package com.example.hotel.controller.refund;

import com.example.hotel.service.order.OrderCancelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements Observer {

    @Autowired
    private OrderCancelService orderCancelService;

    @Override
    public void update(String orderId) {
        // Simulate administrator review operations
        boolean approved = performAdminAudit(orderId); // Assume that the audit result is returned
        if (approved) {
            orderCancelService.processOrderCancellation(Long.valueOf(orderId), 1,"");
        } else {
            orderCancelService.processOrderCancellation(Long.valueOf(orderId), 0,"");
        }
    }

    private boolean performAdminAudit(String orderId) {
        return true; // 默认审核通过
    }
}
