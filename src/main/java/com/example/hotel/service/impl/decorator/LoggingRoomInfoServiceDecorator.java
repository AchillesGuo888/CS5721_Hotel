package com.example.hotel.service.impl.decorator;
import java.util.List;

import com.example.hotel.dto.request.QueryRoomListRequestDTO;
import com.example.hotel.dto.request.QueryRoomRequestDTO;
import com.example.hotel.dto.response.RoomDetailResponse;
import com.example.hotel.exception.BizException;
import com.example.hotel.service.room.RoomInfoService;

public class LoggingRoomInfoServiceDecorator extends RoomInfoServiceDecorator {

    public LoggingRoomInfoServiceDecorator(RoomInfoService wrappedService) {
        super(wrappedService);
    }

    @Override
    public RoomDetailResponse queryRoomInfo(QueryRoomRequestDTO requestDTO) throws BizException {
        System.out.println("Querying room info with request: " + requestDTO);
        RoomDetailResponse response = super.queryRoomInfo(requestDTO);
        System.out.println("Room info response: " + response);
        return response;
    }

    @Override
    public List<RoomDetailResponse> queryRoomList(QueryRoomListRequestDTO requestDTO) throws BizException {
        System.out.println("Querying room list with request: " + requestDTO);
        List<RoomDetailResponse> responseList = super.queryRoomList(requestDTO);
        System.out.println("Room list response: " + responseList);
        return responseList;
    }
}