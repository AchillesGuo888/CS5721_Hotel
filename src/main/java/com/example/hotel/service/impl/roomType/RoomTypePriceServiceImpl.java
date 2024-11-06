package com.example.hotel.service.impl.roomType;

import com.example.hotel.dto.AvailableRoomCountDTO;
import com.example.hotel.entity.RoomTypePrice;
import com.example.hotel.entity.RoomTypePriceExample;
import com.example.hotel.mapper.RoomTypePriceMapper;
import com.example.hotel.mapper.ext.OrderAndDetailMapperExt;
import com.example.hotel.service.order.OrderAndDetailService;
import com.example.hotel.service.roomType.RoomTypePriceService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class RoomTypePriceServiceImpl implements RoomTypePriceService {

  private final RoomTypePriceMapper roomTypePriceMapper;

  @Override
  public List<RoomTypePrice> getPriceByCondition(List<Long> roomTypeIds, LocalDate startDate,
      LocalDate endDate) {
    RoomTypePriceExample example = new RoomTypePriceExample();
    RoomTypePriceExample.Criteria criteria = example.createCriteria();
    criteria.andRoomTypeIdIn(roomTypeIds).andStartDateLessThanOrEqualTo(Date.valueOf(endDate)).andEndDateGreaterThanOrEqualTo(Date.valueOf(startDate));

    return roomTypePriceMapper.selectByExample(example);
  }
}
