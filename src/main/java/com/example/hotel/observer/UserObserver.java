package com.example.hotel.observer;

import com.example.hotel.entity.OrderDetail;

public class UserObserver implements Observer {
    private final Long userId;

    public UserObserver(Long userId) {
        this.userId = userId;
    }

    @Override
    public void update(OrderDetail orderDetail) {
        System.out.println("User " + userId + " received order detail update: " + orderDetail);
    }
}

