package com.example.hotel.service.oder;

import com.example.hotel.entity.OrderDetail;
import com.example.hotel.mapper.OrderDetailMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService {

    private final OrderDetailMapper orderDetailMapper;

    public OrderDetailService(OrderDetailMapper orderDetailMapper) {
        this.orderDetailMapper = orderDetailMapper;
    }

    public List<OrderDetail> getOrderDetailsByOrderId(Long orderId) {
        return orderDetailMapper.getOrderDetailsByOrderId(orderId);
    }

    public void updateOrderDetailStatus(Long id, Integer status) {
        orderDetailMapper.updateOrderDetailStatus(id, status);
    }
}
