package com.example.hotel.service.roomType;


import com.example.hotel.dto.response.AvailableHotelResponse;
import com.example.hotel.entity.RoomTypePrice;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;


public interface RoomTypeInfoService {

    /**
     * get the average price of each room type
     * @param roomTypeIds
     * @param startDate
     * @param endDate
     * @return
     */
    Map<Long, BigDecimal> getAvailableRoomTypeAndPrice(List<Long> roomTypeIds, LocalDate startDate, LocalDate endDate);
}
