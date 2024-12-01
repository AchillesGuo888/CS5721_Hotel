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
public class DiamondPriceServiceImpl implements PriceCalculationService {

  @Override
  public PriceResponse calculateOrderPrice(QueryOrderAmountRequestDTO requestDTO)
      throws BizException {
    //TotalPrice = roomTypePrice* dates* roomCount
    //pointsDiscount = TotalPoints*expensePointLimit
    //expressPoints = TotalPoints
    //memberPrice = TotalPrice* MembershipDiscount
    //if pointsDiscount>memberPrice*expensePointLimit -> pointsDiscount =
    // RealPrice*expensePointLimit
    //                                              -> expressPoints =
    //                                              pointsDiscount/expensePointLimit

    //RealPrice = memberPrice - pointsDiscount
    //EarnPointsCount = RealPrice * pointsRule

    BigDecimal dates = new BigDecimal(
        DateUtil.getBetweenDays(requestDTO.getStartDate(), requestDTO.getEndDate()));
    BigDecimal roomCount = new BigDecimal(requestDTO.getRoomCount());
    BigDecimal totalPrice = requestDTO.getRoomTypePrice().multiply(dates).multiply(roomCount)
        .setScale(2, RoundingMode.HALF_UP);
    BigDecimal realRoomPrice = requestDTO.getRoomTypePrice().multiply(dates)
        .multiply(CommonConstant.DIAMOND_DISCOUNT).setScale(2, RoundingMode.HALF_UP);
    BigDecimal memberPrice = totalPrice.multiply(CommonConstant.DIAMOND_DISCOUNT)
        .setScale(2, RoundingMode.HALF_UP);
//    BigDecimal maxPointDiscount = memberPrice.multiply(CommonConstant.EXPENSE_POINTS_LIMIT)
//    .setScale(2, RoundingMode.HALF_UP);
//    Integer totalPointCount = pointInfoService.getUserPointCount(userId);
//    BigDecimal pointsDiscount = new BigDecimal(totalPointCount).multiply(CommonConstant
//    .EXPENSE_POINTS_RULE).setScale(2, RoundingMode.HALF_UP);
//    Integer expenseCount = totalPointCount;
//    if (pointsDiscount.compareTo(maxPointDiscount)>0){
//      pointsDiscount = maxPointDiscount;
//      expenseCount = maxPointDiscount.divide(CommonConstant.EXPENSE_POINTS_RULE).intValue();
//    }
//    BigDecimal realPrice = memberPrice.subtract(pointsDiscount).setScale(2, RoundingMode.HALF_UP);
    BigDecimal membershipDiscount = totalPrice.subtract(memberPrice)
        .setScale(2, RoundingMode.HALF_UP);
    Integer earnPointCount = memberPrice.multiply(CommonConstant.DIAMOND_POINTS_RULE)
        .setScale(0, RoundingMode.HALF_UP).intValue();
    PriceResponse priceResponse = PriceResponse.builder()
        .earnPointsCount(earnPointCount)
        .membershipDiscount(membershipDiscount)
        .realPrice(memberPrice).totalPrice(totalPrice).realRoomPrice(realRoomPrice)
        .build();

    return priceResponse;
  }

  @Override
  public Integer getEarnPoints(BigDecimal orderPrice) {
    //points = price * point_rule
    Integer earnPointCount = orderPrice.multiply(CommonConstant.DIAMOND_POINTS_RULE)
        .setScale(0, RoundingMode.HALF_UP).intValue();
    return earnPointCount;
  }
}
