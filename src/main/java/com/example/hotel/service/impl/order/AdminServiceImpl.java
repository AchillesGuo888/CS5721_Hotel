package com.example.hotel.service.impl.order;

import com.example.hotel.observer.AdminService;
import com.example.hotel.entity.OrderDetail;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl extends AdminService {

    public void update(OrderDetail orderDetail) {
        // 处理管理员审核订单取消
        System.out.println("管理员处理订单取消审核，订单ID: " + orderDetail.getOrderId());
    }
}
