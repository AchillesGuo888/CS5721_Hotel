package com.example.hotel.service.roomType;


import com.example.hotel.dto.common.RoomTypePriceDTO;
import com.example.hotel.entity.RoomTypePrice;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;


public interface RoomTypePriceService {


  /**
   * according to conditions to query hotels' price
   *
   * @param roomTypeIds
   * @param startDate
   * @param endDate
   * @return
   */
  Map<Long, BigDecimal> getHotelsRoomTypeAndPrice(List<Long> roomTypeIds, LocalDate startDate,
      LocalDate endDate);
	  
  String addRoomTypePrice(RoomTypePriceDTO roomTypePriceDTO);

  RoomTypePriceDTO getRoomTypePrice(Long roomTypeId);

  RoomTypePriceDTO modifyRoomTypePrice(RoomTypePriceDTO requestDTO);

  void deleteRoomTypePrice(Long roomTypeId);
}
