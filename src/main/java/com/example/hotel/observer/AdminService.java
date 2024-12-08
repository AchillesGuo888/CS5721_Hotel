package com.example.hotel.observer;

import com.example.hotel.entity.OrderDetail;
import com.example.hotel.service.order.OrderCancelService;
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
        // 处理订单取消通知
        System.out.println("收到订单取消通知，订单ID：" + orderDetail.getId());
        System.out.println("订单状态：" + orderDetail.getStatus());

        // 模拟审核逻辑
        boolean approved = performAdminAudit(orderDetail);
        if (approved) {
            orderCancelService.processOrderCancellation(orderDetail.getId(), 1, "审核通过");
        } else {
            orderCancelService.processOrderCancellation(orderDetail.getId(), 0, "审核不通过");
        }
    }

    private boolean performAdminAudit(OrderDetail orderDetail) {
        // 假设执行审核逻辑
        System.out.println("正在审核订单，订单ID：" + orderDetail.getId());
        return true; // 默认审核通过
    }
}

