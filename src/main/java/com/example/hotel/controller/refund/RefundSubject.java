package com.example.hotel.controller.refund;

import com.example.hotel.controller.refund.RefundObserver;
import com.example.hotel.entity.Order;

import java.util.ArrayList;
import java.util.List;

public class RefundSubject {
    private List<RefundObserver> observers = new ArrayList<>();

    // addObserver
    public void addObserver(RefundObserver observer) {
        observers.add(observer);
    }

    // removeObserver
    public void removeObserver(RefundObserver observer) {
        observers.remove(observer);
    }

    // notifyObservers
    public void notifyObservers(Order order) {
        for (RefundObserver observer : observers) {
            observer.update(order);
        }
    }

    // refund
    public void refund(Order order) {
        // refund success
        System.out.println("Refund processed for order: " + order.getOrderId());
        // notify all observers
        notifyObservers(order);
    }
}