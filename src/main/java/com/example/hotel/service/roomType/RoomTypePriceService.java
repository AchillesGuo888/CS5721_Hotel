package com.example.hotel.service.roomType;


import com.example.hotel.dto.AvailableRoomCountDTO;
import com.example.hotel.dto.request.QueryRoomTypePriceRequestDTO;
import com.example.hotel.dto.response.RoomAndTypeWithPriceResponse;
import com.example.hotel.entity.RoomTypePrice;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;


public interface RoomTypePriceService {


  /**
   * according to conditions to query hotels' price
   * @param roomTypeIds
   * @param startDate
   * @param endDate
   * @return
   */
  Map<Long, BigDecimal> getHotelsRoomTypeAndPrice(List<Long> roomTypeIds, LocalDate startDate, LocalDate endDate);

}
