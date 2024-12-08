package com.example.hotel.controller.refund;

import com.example.hotel.service.order.OrderCancelService;
import com.example.hotel.service.user.UserService;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class ObserverConfig {

    private final OrderCancelService orderCancelService;
    private final UserService userService;

    public ObserverConfig(OrderCancelService orderCancelService, UserService userService) {
        this.orderCancelService = orderCancelService;
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        // Registering the administrator as an observer
        orderCancelService.addObserver((Observer) userService);
    }
}