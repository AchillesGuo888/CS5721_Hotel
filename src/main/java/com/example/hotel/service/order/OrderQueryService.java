package com.example.hotel.service.order;

import com.example.hotel.dto.response.OrderInfoResponse;
import com.example.hotel.exception.BizException;

public interface OrderQueryService {

  //get basic info of order
  OrderInfoResponse getBaseOrder(Long id) throws BizException;

  //get enhance info from decorators
  OrderInfoResponse queryOrderDetail(Long id) throws BizException;


}
