package com.example.hotel.service.order;


import com.example.hotel.dto.AvailableRoomCountDTO;
import java.time.LocalDate;
import java.util.List;


public interface OrderInfoService {

    List<AvailableRoomCountDTO> queryAvailableRoomType(List<Long> hotelIds, LocalDate startDate,
        LocalDate endDate, Integer quantity);
}
