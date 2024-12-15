package com.example.hotel.service.impl.price;

import com.example.hotel.common.constant.CommonConstant;
import com.example.hotel.dto.request.QueryOrderAmountRequestDTO;
import com.example.hotel.dto.response.PriceResponse;
import com.example.hotel.exception.BizException;
import com.example.hotel.service.price.PriceCalculationService;
import com.example.hotel.util.DateUtil;
import java.math.BigDecimal;
import java.math.RoundingMode;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class SilverPriceServiceImpl implements PriceCalculationService {

  @Override
  public PriceResponse calculateOrderPrice(QueryOrderAmountRequestDTO requestDTO)
      throws BizException {
    BigDecimal dates = new BigDecimal(
        DateUtil.getBetweenDays(requestDTO.getStartDate(), requestDTO.getEndDate()));
    BigDecimal roomCount = new BigDecimal(requestDTO.getRoomCount());
    BigDecimal totalPrice = requestDTO.getRoomTypePrice().multiply(dates).multiply(roomCount)
        .setScale(2, RoundingMode.HALF_UP);
    BigDecimal roomRealTotalPrice = requestDTO.getRoomTypePrice().multiply(dates)
        .multiply(CommonConstant.SILVER_DISCOUNT).setScale(2, RoundingMode.HALF_UP);
    BigDecimal roomDayRealPrice = requestDTO.getRoomTypePrice()
        .multiply(CommonConstant.SILVER_DISCOUNT).setScale(2, RoundingMode.HALF_UP);
    BigDecimal memberPrice = totalPrice.multiply(CommonConstant.SILVER_DISCOUNT)
        .setScale(2, RoundingMode.HALF_UP);

    BigDecimal membershipDiscount = totalPrice.subtract(memberPrice)
        .setScale(2, RoundingMode.HALF_UP);
    Integer earnPointCount = memberPrice.multiply(CommonConstant.SILVER_POINTS_RULE)
        .setScale(0, RoundingMode.HALF_UP).intValue();
    PriceResponse priceResponse = PriceResponse.builder()
        .earnPointsCount(earnPointCount)
        .membershipDiscount(membershipDiscount)
        .orderRealPrice(memberPrice)
        .orderTotalPrice(totalPrice)
        .roomDayRealPrice(roomDayRealPrice)
        .roomTotalPrice(roomRealTotalPrice)
        .build();

    return priceResponse;
  }

  @Override
  public Integer getEarnPoints(BigDecimal orderPrice) {
    //points = price * point_rule
    Integer earnPointCount = orderPrice.multiply(CommonConstant.SILVER_POINTS_RULE)
        .setScale(0, RoundingMode.HALF_UP).intValue();
    return earnPointCount;
  }

  @Override
  public BigDecimal calculateMemberShipRoomTypePrice(BigDecimal roomTypePrice,
      BigDecimal dates) {
    BigDecimal realRoomPrice = roomTypePrice
        .multiply(CommonConstant.SILVER_DISCOUNT).setScale(2, RoundingMode.HALF_UP);
    return realRoomPrice;
  }
}
