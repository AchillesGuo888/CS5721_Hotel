package com.example.hotel.service.price;


import com.example.hotel.dto.request.BookRoomRequestDTO;
import com.example.hotel.dto.request.PrebookRoomRequestDTO;
import com.example.hotel.dto.response.PreBookRoomResponse;
import com.example.hotel.exception.BizException;


public interface OrderInfoService {

    Boolean bookRoom(BookRoomRequestDTO requestDTO, String token) throws BizException;

    PreBookRoomResponse pregenerateOrder(PrebookRoomRequestDTO requestDTO, String token) throws BizException;
}
