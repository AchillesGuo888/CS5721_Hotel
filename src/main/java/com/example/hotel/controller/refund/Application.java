package com.example.hotel.controller.refund;

import com.example.hotel.service.order.OrderCancelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Application {

    @Autowired
    private OrderCancelService orderCancelService;

    @Autowired
    private AdminService adminService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void init() {
        // 注册管理员为观察者
        orderCancelService.addObserver(adminService);
    }
}
