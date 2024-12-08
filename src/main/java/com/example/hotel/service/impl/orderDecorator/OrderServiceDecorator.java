package com.example.hotel.service.impl.orderDecorator;

import com.example.hotel.dto.response.OrderInfoResponse;
import com.example.hotel.exception.BizException;
import com.example.hotel.service.order.OrderQueryService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class OrderServiceDecorator implements OrderQueryService {

  protected final OrderQueryService orderQueryService;

  @Override
  public OrderInfoResponse getBaseOrder(Long orderId) throws BizException {
    return orderQueryService.getBaseOrder(orderId); // get basic service order info
  }

  @Override
  public OrderInfoResponse queryOrderDetail(Long orderId) throws BizException {
    OrderInfoResponse baseOrder = orderQueryService.queryOrderDetail(orderId);
    return enhanceOrder(baseOrder); // enhance order info
  }

  protected OrderInfoResponse enhanceOrder(OrderInfoResponse baseOrder) {
    return baseOrder;
  }
}
