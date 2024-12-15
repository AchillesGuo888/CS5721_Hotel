package com.example.hotel.service.hotel;

import com.example.hotel.dto.request.QueryHotelRequestDTO;
import com.example.hotel.dto.request.QueryRoomTypePriceRequestDTO;
import com.example.hotel.dto.response.AvailableHotelResponse;
import com.example.hotel.dto.response.RoomAndTypeWithPriceResponse;
import java.util.List;


public interface HotelAndTypeService {


  List<AvailableHotelResponse> queryHotelListWithPrice(QueryHotelRequestDTO requestDTO);

  /**
   * according to the hotel id to query the concrete hotel's available room type, count and price
   *
   * @param requestDTO
   * @return
   */
  List<RoomAndTypeWithPriceResponse> getHotelAvailableRoomWithPrice(
      QueryRoomTypePriceRequestDTO requestDTO);

}
