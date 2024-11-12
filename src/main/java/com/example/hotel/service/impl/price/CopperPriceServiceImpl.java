package com.example.hotel.service.impl.price;

import com.example.hotel.dto.request.PrebookRoomRequestDTO;
import com.example.hotel.dto.request.QueryRoomTypePriceRequestDTO;
import com.example.hotel.dto.response.PreBookRoomResponse;
import com.example.hotel.dto.response.PriceResponse;
import com.example.hotel.dto.response.RoomAndTypeWithPriceResponse;
import com.example.hotel.entity.RoomTypeInfo;
import com.example.hotel.entity.RoomTypeInfoExample;
import com.example.hotel.entity.User;
import com.example.hotel.exception.BizException;
import com.example.hotel.mapper.RoomTypeInfoMapper;
import com.example.hotel.service.price.PriceCalculationService;
import com.example.hotel.service.roomType.RoomTypeInfoService;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class CooperPriceServiceImpl implements PriceCalculationService {

  private final static BigDecimal MEMBERSHIP_DISCOUNT = new BigDecimal(0.9);

  @Override
  public PriceResponse calculateOrderPrice(PrebookRoomRequestDTO requestDTO,
      PreBookRoomResponse result, User userInfo) throws BizException {
    return null;
  }
}
