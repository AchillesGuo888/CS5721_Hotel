package com.example.hotel.service.order;


import com.example.hotel.dto.AvailableRoomCountDTO;
import com.example.hotel.dto.request.BookRoomRequestDTO;
import com.example.hotel.dto.request.PrebookRoomRequestDTO;
import com.example.hotel.dto.response.PreBookRoomResponse;
import com.example.hotel.exception.BizException;
import java.time.LocalDate;
import java.util.List;


public interface OrderInfoService {

    Boolean bookRoom(BookRoomRequestDTO requestDTO, String token) throws BizException;

    PreBookRoomResponse pregenerateOrder(PrebookRoomRequestDTO requestDTO, String token) throws BizException;
}
