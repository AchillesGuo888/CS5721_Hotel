package com.example.hotel.service.impl.price;

import com.example.hotel.dto.request.PrebookRoomRequestDTO;
import com.example.hotel.dto.response.PreBookRoomResponse;
import com.example.hotel.dto.response.PriceResponse;
import com.example.hotel.entity.User;
import com.example.hotel.exception.BizException;
import com.example.hotel.service.price.PriceCalculationService;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class GoldPriceServiceImpl implements PriceCalculationService {

  private final static BigDecimal MEMBERSHIP_DISCOUNT = new BigDecimal(0.9);

  @Override
  public PriceResponse calculateOrderPrice(PrebookRoomRequestDTO requestDTO,
      PreBookRoomResponse result, User userInfo) throws BizException {
    return null;
  }
}
