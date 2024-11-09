package com.example.hotel.service.impl.roomType;

import com.example.hotel.entity.RoomTypePrice;
import com.example.hotel.entity.RoomTypePriceExample;
import com.example.hotel.mapper.RoomTypePriceMapper;

import com.example.hotel.service.roomType.RoomTypeInfoService;
import com.example.hotel.service.roomType.RoomTypePriceService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class RoomTypePriceServiceImpl implements RoomTypePriceService {

  private final RoomTypePriceMapper roomTypePriceMapper;
  private final RoomTypeInfoService roomTypeInfoService;

  @Override
  public Map<Long, BigDecimal> getHotelsRoomTypeAndPrice(List<Long> roomTypeIds,
      LocalDate startDate, LocalDate endDate) {

    //get special price in a period
    Map<Long, Map<LocalDate, BigDecimal>> specialPriceMap = getSpecialPriceMap(roomTypeIds,startDate,endDate);
    //get default price(weekday and weekend)
    Map<Long, Map<String, BigDecimal>> defaultPriceMap = roomTypeInfoService.getDefaultPrices(roomTypeIds);
    //get date range (weekday or weekend)
    List<LocalDate> dateRange = generateDateRange(startDate, endDate);
    //calculate each type price
    Map<Long, BigDecimal> averagePrices = getAveragePrices(roomTypeIds,specialPriceMap,defaultPriceMap,dateRange);


    return averagePrices;
  }

  private Map<Long, BigDecimal> getAveragePrices(List<Long> roomTypeIds, Map<Long, Map<LocalDate, BigDecimal>> specialPriceMap, Map<Long, Map<String, BigDecimal>> defaultPriceMap, List<LocalDate> dateRange) {
    Map<Long, BigDecimal> averagePrices = new HashMap<>();
    for (Long id: roomTypeIds){
      BigDecimal total = new BigDecimal(0);
      int count = 0;

      Map<LocalDate, BigDecimal> specialMap = specialPriceMap.getOrDefault(id, new HashMap<>());
      Map<String, BigDecimal> defaultMap = defaultPriceMap.getOrDefault(id, new HashMap<>());

      for (LocalDate date : dateRange) {
        BigDecimal price = specialMap.get(date);
        if (price == null) {
          // 使用默认价格，判断是平日还是周末
          price = isWeekend(date) ? defaultMap.get("weekend") : defaultMap.get("weekday");
        }
        total = total.add(price);
        count++;
      }

      BigDecimal averagePrice = total.divide(BigDecimal.valueOf(count), 2, RoundingMode.HALF_UP);

      averagePrices.put(id, averagePrice);
    }
    return averagePrices;
  }

  //judge weekday or weekend
  private boolean isWeekend(LocalDate date) {
    int dayOfWeek = date.getDayOfWeek().getValue();
    return dayOfWeek == 6 || dayOfWeek == 7; // 6=Saturday, 7=Sunday
  }


  private Map<Long, Map<LocalDate, BigDecimal>> getSpecialPriceMap(List<Long> roomTypeIds, LocalDate startDate, LocalDate endDate) {
    Map<Long, Map<LocalDate, BigDecimal>> specialMap = new HashMap<>();
    List<RoomTypePrice> specialList = getPriceByCondition(roomTypeIds,startDate,endDate);
    if (CollectionUtils.isEmpty(specialList)){
      return specialMap;
    }

    for (RoomTypePrice item: specialList){
      specialMap.putIfAbsent(item.getRoomTypeId(), new HashMap<>());
      LocalDate start = item.getStartDate().toInstant()
          .atZone(ZoneId.systemDefault())
          .toLocalDate();
      LocalDate end = item.getEndDate().toInstant()
          .atZone(ZoneId.systemDefault())
          .toLocalDate();
      for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
        if (!date.isBefore(startDate) && !date.isAfter(endDate)) {
          specialMap.get(item.getRoomTypeId()).put(date, item.getPrice());
        }
      }
    }
    return specialMap;
  }

  private List<RoomTypePrice> getPriceByCondition(List<Long> roomTypeIds, LocalDate startDate,
      LocalDate endDate) {
    RoomTypePriceExample example = new RoomTypePriceExample();
    RoomTypePriceExample.Criteria criteria = example.createCriteria();
    criteria.andRoomTypeIdIn(roomTypeIds).andStartDateLessThanOrEqualTo(Date.valueOf(endDate)).andEndDateGreaterThanOrEqualTo(Date.valueOf(startDate));

    return roomTypePriceMapper.selectByExample(example);
  }

  private List<LocalDate> generateDateRange(LocalDate startDate, LocalDate endDate) {
    List<LocalDate> dates = new ArrayList<>();
    LocalDate date = startDate;
    while (!date.isAfter(endDate)) {
      dates.add(date);
      date = date.plusDays(1);
    }
    return dates;
  }
}
