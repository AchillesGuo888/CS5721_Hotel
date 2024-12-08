package com.example.hotel.service.price;


import com.example.hotel.dto.request.QueryOrderAmountRequestDTO;
import com.example.hotel.dto.response.PriceResponse;
import com.example.hotel.exception.BizException;
import java.math.BigDecimal;


public interface PriceCalculationService {

  PriceResponse calculateOrderPrice(QueryOrderAmountRequestDTO requestDTO) throws BizException;

  Integer getEarnPoints(BigDecimal orderPrice);

  BigDecimal calculateMemberShipRoomTypePrice(BigDecimal roomTypePrice, BigDecimal dates);
}
