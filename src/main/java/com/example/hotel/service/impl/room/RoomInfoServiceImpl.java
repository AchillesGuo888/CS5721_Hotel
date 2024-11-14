package com.example.hotel.service.impl.room;

import com.example.hotel.dto.request.AddRoomRequestDTO;
import com.example.hotel.dto.request.CheckInRequestDTO;
import com.example.hotel.dto.request.DeleteRoomRequestDTO;
import com.example.hotel.dto.request.ModifyRoomInfoRequestDTO;
import com.example.hotel.dto.request.QueryRoomListRequestDTO;
import com.example.hotel.dto.request.QueryRoomRequestDTO;
import com.example.hotel.dto.request.QueryRoomTypePriceRequestDTO;
import com.example.hotel.dto.response.CheckInResponse;
import com.example.hotel.dto.response.RoomAndTypeWithPriceResponse;
import com.example.hotel.dto.response.RoomDetailResponse;
import com.example.hotel.entity.RoomInfo;
import com.example.hotel.entity.RoomInfoExample;
import com.example.hotel.entity.RoomTypeInfo;
import com.example.hotel.entity.RoomTypeInfoExample;
import com.example.hotel.exception.BizException;
import com.example.hotel.mapper.RoomInfoMapper;
import com.example.hotel.mapper.RoomTypeInfoMapper;

import com.example.hotel.service.room.RoomInfoService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class RoomInfoServiceImpl implements RoomInfoService {

    private final RoomInfoMapper roomInfoMapper;

    @Override
    public void addRoom(AddRoomRequestDTO requestDTO) throws BizException {
        // Create a RoomInfo entity from the AddRoomRequestDTO
        RoomInfo roomInfo = new RoomInfo();
        
        // Set the room attributes based on the DTO
        roomInfo.setHotelId(requestDTO.getHotelId());
        roomInfo.setRoomTypeId(requestDTO.getRoomTypeId());
        roomInfo.setRoomNumber(requestDTO.getRoomNumber());

        // Insert the room info into the database
        roomInfoMapper.insertSelective(roomInfo);
        
        log.info("Room info added successfully: {} in hotel {}", roomInfo.getRoomNumber(), roomInfo.getHotelId());
    }

    @Override
    public void modifyRoomInfo(ModifyRoomInfoRequestDTO requestDTO) {
    // Retrieve the existing room info based on room ID
        RoomInfo roomInfo = roomInfoMapper.selectByPrimaryKey(requestDTO.getRoomId());
        
        if (roomInfo == null) {
            throw new RuntimeException("Room not found with ID: " + requestDTO.getRoomId());
        }

        // Update the fields of the room info
        roomInfo.setRoomNumber(requestDTO.getRoomNumber());
        roomInfo.setRoomKey(requestDTO.getRoomKey());
        roomInfo.setHotelId(requestDTO.getHotelId());
        roomInfo.setRoomTypeId(requestDTO.getRoomTypeId());
        roomInfo.setUpdateTime(requestDTO.getUpdateTime()); // Set the update time
        roomInfo.setIsDeleted(requestDTO.getIsDeleted());

        // Update the room info in the database
        roomInfoMapper.updateByPrimaryKeySelective(roomInfo);
        
        log.info("Room info updated: {}", roomInfo.getRoomNumber());
    }

    @Override
    public void deleteRoom(DeleteRoomRequestDTO requestDTO) {
        // Retrieve the existing room info based on room ID
        RoomInfo roomInfo = roomInfoMapper.selectByPrimaryKey(requestDTO.getRoomId());
        
        if (roomInfo == null) {
            throw new RuntimeException("Room not found with ID: " + requestDTO.getRoomId());
        }

        // Set the room as deleted
        roomInfo.setIsDeleted((byte) 1);  // 1 indicates deleted

        // Update the room info to reflect deletion
        roomInfoMapper.updateByPrimaryKeySelective(roomInfo);
        
        log.info("Room deleted: {}", roomInfo.getRoomNumber());
    }

    @Override
    public CheckInResponse checkInRoom(CheckInRequestDTO requestDTO) {
        // Retrieve the room info based on room ID
        RoomInfo roomInfo = roomInfoMapper.selectByPrimaryKey(requestDTO.getRoomId());

        if (roomInfo == null || roomInfo.getIsDeleted() == 1) {
            throw new RuntimeException("Room not available for check-in or deleted.");
        }

        // Here, we would also update the room status (e.g., marked as occupied)
        // For simplicity, let's assume we have a room status field (roomStatus) in RoomInfo or elsewhere.

        // Perform check-in operation (e.g., update room status to occupied)
        roomInfo.setUpdateTime(requestDTO.getUpdateTime()); // Update time to current timestamp
        // roomInfo.setRoomStatus("occupied");  // Example of updating the room status (if applicable)

        roomInfoMapper.updateByPrimaryKeySelective(roomInfo);

        // Return response with details of the room and status
        return CheckInResponse.builder()
            .roomNumber(roomInfo.getRoomNumber())  // Set room number
            .roomKey(roomInfo.getRoomKey())        // Set room key (if needed)
            .build();
    }

    @Override
    public RoomDetailResponse queryRoom(QueryRoomRequestDTO requestDTO) {
        // Retrieve the room info based on room ID
        RoomInfo roomInfo = roomInfoMapper.selectByPrimaryKey(requestDTO.getRoomId());

        if (roomInfo == null || roomInfo.getIsDeleted() == 1) {
            throw new RuntimeException("Room not found or deleted.");
        }

        // Map RoomInfo entity to RoomDetailResponse DTO
        return RoomDetailResponse.builder()
          .roomId(roomInfo.getId())
          .roomNumber(roomInfo.getRoomNumber())
          .roomKey(roomInfo.getRoomKey())
          .hotelId(roomInfo.getHotelId())
          .roomTypeId(roomInfo.getRoomTypeId())
          .createTime(roomInfo.getCreateTime())
          .updateTime(roomInfo.getUpdateTime())
          .build();
    }

    @Override
    public List<RoomDetailResponse> queryRoomList(QueryRoomListRequestDTO requestDTO) {
        // Query room list based on provided parameters (e.g., hotelId, roomTypeId)
        RoomInfoExample example = new RoomInfoExample();
        RoomInfoExample.Criteria criteria = example.createCriteria();

        // Set criteria for hotelId and roomTypeId based on requestDTO
        criteria.andHotelIdEqualTo(requestDTO.getHotelId());
        criteria.andRoomTypeIdEqualTo(requestDTO.getRoomTypeId());

        // Fetch the list of RoomInfo objects based on the criteria in RoomListExample
        List<RoomInfo> roomList = roomInfoMapper.selectByExample(example);

        if (roomList == null || roomList.isEmpty()) {
            throw new RuntimeException("No rooms found for the given criteria.");
        }

        // Map each RoomInfo to RoomDetailResponse
        List<RoomDetailResponse> responseList = roomList.stream()
          .map(roomInfo -> RoomDetailResponse.builder()
              .roomId(roomInfo.getId())
              .roomNumber(roomInfo.getRoomNumber())
              .roomKey(roomInfo.getRoomKey())
              .hotelId(roomInfo.getHotelId())
              .roomTypeId(roomInfo.getRoomTypeId())
              .createTime(roomInfo.getCreateTime())
              .updateTime(roomInfo.getUpdateTime())
              .build())
          .collect(Collectors.toList());

        return responseList;
    }

}