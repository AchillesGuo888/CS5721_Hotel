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
     * 取消订单并通知观察者
     * @param orderId 订单ID
     * @param isApproved 审核是否通过
     * @param cancelReason 取消原因
     * @return 取消结果
     */
    public String cancelOrderAndNotify(Long orderId, Integer isApproved, String cancelReason) {
        // 1. 调用 OrderCancelService 取消订单
        String cancelResult = orderCancelService.processOrderCancellation(orderId, isApproved, cancelReason);

        if ("订单取消成功，积分已回滚，退款已处理".equals(cancelResult)) {
            // 2. 通知观察者
            OrderDetail orderDetail = orderCancelService.getOrderDetailById(orderId);
            observerManager.notifyObservers(orderDetail); // 发布通知
        }

        return cancelResult;
    }


    /**
     * 取消订单并通知观察者
     * @param orderId 订单ID
     * @param roomNumber 要取消的房间号列表
     * @param cancelReason 取消原因
     * @param isApproved 审核是否通过
     * @return 取消结果
     */
    @Transactional
    public String cancelOrderAndNotify(Long orderId, Long roomNumber, String cancelReason, int isApproved) {
        // 1. 调用 OrderCancelService 取消房间
        String cancelResult = orderCancelService.cancelRoomInOrder(orderId, roomNumber, cancelReason, isApproved);

        if ("订单取消成功，积分已回滚，退款已处理".equals(cancelResult)) {
            // 2. 通知观察者
            OrderDetail orderDetail = orderCancelService.getOrderDetailById(orderId);
            observerManager.notifyObservers(orderDetail); // 发布通知
        }

        return "订单取消成功，已通知观察者";
    }
}
