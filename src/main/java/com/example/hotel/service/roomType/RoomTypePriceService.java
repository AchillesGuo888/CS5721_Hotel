package com.example.hotel.service.roomType;


import com.example.hotel.dto.AvailableRoomCountDTO;
import com.example.hotel.entity.RoomTypePrice;
import java.time.LocalDate;
import java.util.List;


public interface RoomTypePriceService {

    List<RoomTypePrice> getPriceByCondition(List<Long> roomTypeIds, LocalDate startDate,
        LocalDate endDate);
}
