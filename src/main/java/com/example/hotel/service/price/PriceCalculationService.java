package com.example.hotel.service.price;



import com.example.hotel.dto.request.PrebookRoomRequestDTO;
import com.example.hotel.dto.response.PreBookRoomResponse;
import com.example.hotel.dto.response.PriceResponse;

import com.example.hotel.exception.BizException;
import java.math.BigDecimal;


public interface PriceCalculationService {

  PriceResponse calculateOrderPrice(PrebookRoomRequestDTO requestDTO, BigDecimal originalPrice,
      String userId) throws BizException;
}
