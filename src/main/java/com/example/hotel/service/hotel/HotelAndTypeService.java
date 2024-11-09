package com.example.hotel.service.hotel;

import com.example.hotel.dto.request.ForgetPasswordRequestDTO;
import com.example.hotel.dto.request.ModifyUserInfoRequestDTO;
import com.example.hotel.dto.request.PasswordModifyRequestDTO;
import com.example.hotel.dto.request.QueryHotelRequestDTO;
import com.example.hotel.dto.request.QueryRoomTypePriceRequestDTO;
import com.example.hotel.dto.request.RegisterRequestDTO;
import com.example.hotel.dto.request.UserLoginRequestDTO;
import com.example.hotel.dto.response.AvailableHotelResponse;
import com.example.hotel.dto.response.RegisterResponse;
import com.example.hotel.dto.response.RoomAndTypeWithPriceResponse;
import com.example.hotel.dto.response.UpdateInfoResponse;
import com.example.hotel.dto.response.UserInfoResponse;
import com.example.hotel.exception.BizException;
import java.util.List;


public interface HotelAndTypeService {


  List<AvailableHotelResponse> queryHotelListWithPrice(QueryHotelRequestDTO requestDTO);

  /**
   * according to the hotel id to query the concrete hotel's available room type, count and price
   * @param requestDTO
   * @return
   */
  List<RoomAndTypeWithPriceResponse> getHotelAvailableRoomWithPrice(QueryRoomTypePriceRequestDTO requestDTO);

}
