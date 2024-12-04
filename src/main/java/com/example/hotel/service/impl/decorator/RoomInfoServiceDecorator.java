package com.example.hotel.service.impl.decorator;
import java.util.List;

import com.example.hotel.dto.request.AddRoomRequestDTO;
import com.example.hotel.dto.request.CheckInRequestDTO;
import com.example.hotel.dto.request.DeleteRoomRequestDTO;
import com.example.hotel.dto.request.ModifyRoomInfoRequestDTO;
import com.example.hotel.dto.request.QueryRoomListRequestDTO;
import com.example.hotel.dto.request.QueryRoomRequestDTO;
import com.example.hotel.dto.response.CheckInResponse;
import com.example.hotel.dto.response.RoomDetailResponse;
import com.example.hotel.exception.BizException;
import com.example.hotel.service.room.RoomInfoService;

public abstract class RoomInfoServiceDecorator implements RoomInfoService {
    protected RoomInfoService wrappedService;

    public RoomInfoServiceDecorator(RoomInfoService wrappedService) {
        this.wrappedService = wrappedService;
    }

    @Override
    public void addRoom(AddRoomRequestDTO requestDTO) throws BizException {
        wrappedService.addRoom(requestDTO);
    }

    @Override
    public void modifyRoomInfo(ModifyRoomInfoRequestDTO requestDTO) throws BizException {
        wrappedService.modifyRoomInfo(requestDTO);
    }

    @Override
    public void deleteRoom(DeleteRoomRequestDTO requestDTO) throws BizException {
        wrappedService.deleteRoom(requestDTO);
    }

    @Override
    public CheckInResponse checkInRoom(CheckInRequestDTO requestDTO) throws BizException {
        return wrappedService.checkInRoom(requestDTO);
    }

    @Override
    public RoomDetailResponse queryRoomInfo(QueryRoomRequestDTO requestDTO) throws BizException {
        return wrappedService.queryRoomInfo(requestDTO);
    }

    @Override
    public List<RoomDetailResponse> queryRoomList(QueryRoomListRequestDTO requestDTO) throws BizException {
        return wrappedService.queryRoomList(requestDTO);
    }
}

