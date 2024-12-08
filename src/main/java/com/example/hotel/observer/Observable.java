package com.example.hotel.observer;

import com.example.hotel.entity.OrderDetail;

public interface Observable {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(OrderDetail orderDetail);
}