package com.example.hotel.service.roomType;


import com.example.hotel.dto.request.AddRoomTypeRequestDTO;
import com.example.hotel.dto.request.ModifyRoomTypeInfoRequestDTO;
import com.example.hotel.dto.request.QueryRoomTypePriceRequestDTO;
import com.example.hotel.dto.response.AvailableHotelResponse;
import com.example.hotel.dto.response.HotelDetailResponse;
import com.example.hotel.dto.response.RoomAndTypeWithPriceResponse;
import com.example.hotel.entity.RoomTypeInfo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


public interface RoomTypeInfoService {

  /**
   * get room type default price by room type ids
   *
   * @param roomTypeIds
   * @return
   */
  Map<Long, Map<String, BigDecimal>> getDefaultPrices(List<Long> roomTypeIds);

  RoomTypeInfo getRoomTypeById(Long id);
  
  String addRoomType(AddRoomTypeRequestDTO roomTypeRequestDTO);

    HotelDetailResponse getRoomOnId(Long id);

  String  updateRoomType(ModifyRoomTypeInfoRequestDTO requestDto);

}
