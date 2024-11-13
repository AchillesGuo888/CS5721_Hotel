package com.example.hotel.service.order;


import com.example.hotel.dto.AvailableRoomCountDTO;
import com.example.hotel.dto.request.BookRoomRequestDTO;
import com.example.hotel.exception.BizException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;


public interface OrderAndDetailService {

    List<AvailableRoomCountDTO> queryAvailableRoomType(List<Long> hotelIds,
        LocalDate startDate, LocalDate endDate,Integer quantity, List<Long> roomTypeIds);

    void addOrderDetail(Long orderBaseId,
        BookRoomRequestDTO requestDTO);
}
