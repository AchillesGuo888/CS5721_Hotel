package com.example.hotel.observer;

import com.example.hotel.entity.OrderDetail;

public interface Observer {
    void update(OrderDetail orderDetail);
}