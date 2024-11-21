package com.example.hotel.service.refund;

import com.example.hotel.controller.refund.RefundOrderStatusObserver;
import com.example.hotel.controller.refund.RefundPointsObserver;
import com.example.hotel.controller.refund.RefundSubject;
import com.example.hotel.entity.Order;

public class RefundService {

    public void processRefund(Order order) {
        // Creating an Observable
        RefundSubject refundSubject = new RefundSubject();

        // Adding specific observers
        refundSubject.addObserver(new RefundOrderStatusObserver());
        refundSubject.addObserver(new RefundPointsObserver());

        // Trigger notification to all observers
        refundSubject.notifyObservers(order);

        System.out.println("Refund process completed for order: " + order.getOrderId());
    }
}
