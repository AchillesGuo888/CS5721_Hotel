package com.example.hotel.service.order.cancelorder;

import com.example.hotel.entity.OrderDetail;
import com.example.hotel.mapper.OrderDetailMapper;
import com.example.hotel.observer.ObserverManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CancelNotifyService {

    private final OrderCancelService orderCancelService;
    private final ObserverManager observerManager;

    @Autowired
    public CancelNotifyService(OrderCancelService orderCancelService, ObserverManager observerManager) {
        this.orderCancelService = orderCancelService;
        this.observerManager = observerManager;
    }

    /**
     * Cancel the order and notify the observer
     * @param orderId order ID
     * @param isApproved whether the review is passed
     * @param cancelReason cancellation reason
     * @return cancellation result
     */
    public String cancelOrderAndNotify(Long orderId, Integer isApproved, String cancelReason) {
        // 1. Call OrderCancelService to cancel the order
        String cancelResult = orderCancelService.processOrderCancellation(orderId, isApproved, cancelReason);

        if ("Order canceled successfully, points rolled back, refund processed".equals(cancelResult)) {
            // 2. Notify observers
            OrderDetail orderDetail = orderCancelService.getOrderDetailById(orderId);
            observerManager.notifyObservers(orderDetail); // Publish notification
        }

        return cancelResult;
    }


    /**
     * Cancel the order and notify the observer
     * @param orderId order ID
     * @param roomNumber list of room numbers to be canceled
     * @param cancelReason cancellation reason
     * @param isApproved review passed
     * @return cancellation result
     */
    @Transactional
    public String cancelOrderAndNotify(Long orderId, Long roomNumber, String cancelReason, int isApproved) {
        // 1. Call OrderCancelService to cancel the room
        String cancelResult = orderCancelService.cancelRoomInOrder(orderId, roomNumber, cancelReason, isApproved);

        if ("Order canceled successfully, points rolled back, refund processed".equals(cancelResult)) {
            // 2. Notify observers
            OrderDetail orderDetail = orderCancelService.getOrderDetailById(orderId);
            observerManager.notifyObservers(orderDetail); // Publish notification
        }

        return "Order canceled successfully, observers notified";
    }
}
