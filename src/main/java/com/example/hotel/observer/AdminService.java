package com.example.hotel.observer;

import com.example.hotel.entity.OrderDetail;
import com.example.hotel.service.order.cancelorder.OrderCancelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements Observer {

    private final OrderCancelService orderCancelService;

    @Autowired
    public AdminService(OrderCancelService orderCancelService) {
        this.orderCancelService = orderCancelService;
    }

    @Override
    public void update(OrderDetail orderDetail) {
        // Processing order cancellation notifications
        System.out.println("Received order cancellation notification, order ID：" + orderDetail.getId());
        System.out.println("Order Status：" + orderDetail.getStatus());

        // Simulation audit logic
        boolean approved = performAdminAudit(orderDetail);
        if (approved) {
            orderCancelService.processOrderCancellation(orderDetail.getId(), 1, "Approved");
        } else {
            orderCancelService.processOrderCancellation(orderDetail.getId(), 0, "Failed the audit");
        }
    }

    private boolean performAdminAudit(OrderDetail orderDetail) {
        // Assume that the audit logic is executed
        System.out.println("Reviewing order, order ID：" + orderDetail.getId());
        return true; // Default approval
    }
}

