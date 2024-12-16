package com.example.hotel.service.impl.orderDecorator;

import cn.hutool.core.util.ObjectUtil;
import com.example.hotel.common.base.ResponseCode;
import com.example.hotel.dto.response.OrderInfoResponse;
import com.example.hotel.entity.OrderBase;
import com.example.hotel.exception.BizException;
import com.example.hotel.mapper.OrderBaseMapper;
import com.example.hotel.service.order.OrderQueryService;
import com.example.hotel.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class BasicOrderService implements OrderQueryService {

  private final OrderBaseMapper orderBaseMapper;

  @Override
  public OrderInfoResponse getBaseOrder(Long id) throws BizException {
    //get order base info
    OrderBase orderBase = orderBaseMapper.selectByPrimaryKey(id);
    if (ObjectUtil.isNull(orderBase)) {
      throw new BizException(ResponseCode.order_error);
    }

    //get order basic info and other info will be added by decorator
    OrderInfoResponse result = OrderInfoResponse.builder().build();
    BeanUtils.copyProperties(orderBase, result);
    result.setOrderId(orderBase.getId());
    result.setMembershipDiscount(orderBase.getTotalPrice().subtract(orderBase.getRealPrice()));
    result.setDates( DateUtil.getBetweenDays(orderBase.getStartDate(), orderBase.getEndDate()).intValue());
    return result;
  }

  @Override
  public OrderInfoResponse queryOrderDetail(Long id) throws BizException {
    return getBaseOrder(id);
  }
}
