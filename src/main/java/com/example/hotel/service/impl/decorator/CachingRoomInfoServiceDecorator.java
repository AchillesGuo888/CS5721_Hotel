package com.example.hotel.service.impl.decorator;

import java.util.HashMap;
import java.util.Map;

import com.example.hotel.dto.request.QueryRoomRequestDTO;
import com.example.hotel.dto.response.RoomDetailResponse;
import com.example.hotel.exception.BizException;
import com.example.hotel.service.room.RoomInfoService;

public class CachingRoomInfoServiceDecorator extends RoomInfoServiceDecorator {
    private Map<Long, RoomDetailResponse> roomCache = new HashMap<>();

    public CachingRoomInfoServiceDecorator(RoomInfoService wrappedService) {
        super(wrappedService);
    }

    @Override
    public RoomDetailResponse queryRoomInfo(QueryRoomRequestDTO requestDTO) throws BizException {
        Long roomId = requestDTO.getRoomId();
        if (roomCache.containsKey(roomId)) {
            System.out.println("Returning cached response for room ID: " + roomId);
            return roomCache.get(roomId);
        }
        RoomDetailResponse response = super.queryRoomInfo(requestDTO);
        roomCache.put(roomId, response);
        return response;
    }
}

