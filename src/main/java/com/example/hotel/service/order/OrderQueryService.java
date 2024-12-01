package com.example.hotel.service.order;

import com.example.hotel.dto.response.OrderInfoResponse;
import com.example.hotel.exception.BizException;

public interface OrderQueryService {

  OrderInfoResponse queryOrderDetail(Long id) throws BizException;
}
