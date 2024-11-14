package com.example.hotel.service.impl.price;

import com.example.hotel.common.constant.CommonConstant;
import com.example.hotel.dto.request.PrebookRoomRequestDTO;
import com.example.hotel.dto.response.PreBookRoomResponse;
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
  public PriceResponse calculateOrderPrice(PrebookRoomRequestDTO requestDTO,
      BigDecimal originalPrice, String userId) throws BizException {
    //TotalPrice = roomTypePrice* dates* roomCount
    //pointsDiscount = TotalPoints*expensePointLimit
    //expressPoints = TotalPoints
    //memberPrice = TotalPrice* MembershipDiscount
    //if pointsDiscount>memberPrice*expensePointLimit -> pointsDiscount = RealPrice*expensePointLimit
    //                                              -> expressPoints = pointsDiscount/expensePointLimit

    //RealPrice = memberPrice - pointsDiscount
    //EarnPointsCount = RealPrice * pointsRule

    BigDecimal roomTypePrice = originalPrice;
    BigDecimal dates = new BigDecimal(DateUtil.getBetweenDays(requestDTO.getStartDate(),requestDTO.getEndDate()));
    BigDecimal roomCount = new BigDecimal(requestDTO.getRoomCount());
    BigDecimal totalPrice = roomTypePrice.multiply(dates).multiply(roomCount).setScale(2, RoundingMode.HALF_UP);
    BigDecimal realRoomPrice = roomTypePrice.multiply(CommonConstant.SILVER_DISCOUNT).setScale(2, RoundingMode.HALF_UP);

    BigDecimal memberPrice = totalPrice.multiply(CommonConstant.SILVER_DISCOUNT).setScale(2, RoundingMode.HALF_UP);
//    BigDecimal maxPointDiscount = memberPrice.multiply(CommonConstant.EXPENSE_POINTS_LIMIT).setScale(2, RoundingMode.HALF_UP);
//    Integer totalPointCount = pointInfoService.getUserPointCount(userId);
//    BigDecimal pointsDiscount = new BigDecimal(totalPointCount).multiply(CommonConstant.EXPENSE_POINTS_RULE).setScale(2, RoundingMode.HALF_UP);
//    Integer expenseCount = totalPointCount;
//    if (pointsDiscount.compareTo(maxPointDiscount)>0){
//      pointsDiscount = maxPointDiscount;
//      expenseCount = maxPointDiscount.divide(CommonConstant.EXPENSE_POINTS_RULE).intValue();
//    }
//    BigDecimal realPrice = memberPrice.subtract(pointsDiscount).setScale(2, RoundingMode.HALF_UP);
    BigDecimal membershipDiscount = totalPrice.subtract(memberPrice).setScale(2, RoundingMode.HALF_UP);
    Integer earnPointCount = memberPrice.multiply(CommonConstant.SILVER_POINTS_RULE).setScale(0, RoundingMode.HALF_UP).intValue();
    PriceResponse priceResponse = PriceResponse.builder()
        .earnPointsCount(earnPointCount)
        .membershipDiscount(membershipDiscount)
        .realPrice(memberPrice).totalPrice(totalPrice).realRoomPrice(realRoomPrice)
        .build();

    return priceResponse;
  }
}
