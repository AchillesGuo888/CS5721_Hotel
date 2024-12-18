package com.example.hotel.service.room;


import com.example.hotel.dto.request.AddRoomRequestDTO;
import com.example.hotel.dto.request.CheckInRequestDTO;
import com.example.hotel.dto.request.DeleteRoomRequestDTO;
import com.example.hotel.dto.request.ModifyRoomInfoRequestDTO;
import com.example.hotel.dto.request.QueryRoomRequestDTO;
import com.example.hotel.dto.request.QueryRoomListRequestDTO;
import com.example.hotel.dto.response.AvailableHotelResponse;
import com.example.hotel.dto.response.CheckInResponse;
import com.example.hotel.dto.response.RoomDetailResponse;
import com.example.hotel.entity.RoomTypePrice;
import com.example.hotel.exception.BizException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;


public interface RoomInfoService {

    void addRoom(AddRoomRequestDTO requestDTO) throws BizException;

    // Method to modify room details
    void modifyRoomInfo(ModifyRoomInfoRequestDTO requestDTO) throws BizException;

    // Method to delete a room
    void deleteRoom(DeleteRoomRequestDTO requestDTO) throws BizException;

    // Method to check in a guest to a room
    CheckInResponse checkInRoom(CheckInRequestDTO requestDTO) throws BizException;

    RoomDetailResponse queryRoomInfo(QueryRoomRequestDTO requestDTO) throws BizException;

    List<RoomDetailResponse> queryRoomList(QueryRoomListRequestDTO requestDTO) throws BizException;
}
