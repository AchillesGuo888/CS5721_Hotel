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
        // 模拟管理员审核操作
        boolean approved = performAdminAudit(orderId); // 假设返回审核结果
        if (approved) {
            orderCancelService.processOrderCancellation(Long.valueOf(orderId), true);
        } else {
            orderCancelService.processOrderCancellation(Long.valueOf(orderId), false);
        }
    }

    private boolean performAdminAudit(String orderId) {
        return true; // 默认审核通过
    }
}
