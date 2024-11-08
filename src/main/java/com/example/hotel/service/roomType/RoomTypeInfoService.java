package com.example.hotel.service.roomType;


import com.example.hotel.dto.request.QueryRoomTypePriceRequestDTO;
import com.example.hotel.dto.response.AvailableHotelResponse;
import com.example.hotel.dto.response.RoomAndTypeWithPriceResponse;
import com.example.hotel.entity.RoomTypePrice;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;


public interface RoomTypeInfoService {

  /**
   * get the room type price list of the hotel for booking
   * @param requestDTO
   * @return
   */
    List<RoomAndTypeWithPriceResponse> getHotelAvailableRoomWithPrice(QueryRoomTypePriceRequestDTO requestDTO);

    /**
     * get room type default price by room type ids
     * @param roomTypeIds
     * @return
     */
    Map<Long, Map<String, BigDecimal>> getDefaultPrices(List<Long> roomTypeIds);
}
