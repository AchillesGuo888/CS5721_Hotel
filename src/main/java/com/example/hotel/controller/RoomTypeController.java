package com.example.hotel.controller;


import com.example.hotel.common.base.ResponseResult;
import com.example.hotel.dto.request.AddHotelRequestDTO;
import com.example.hotel.dto.request.AddRoomTypeRequestDTO;
import com.example.hotel.dto.request.DeleteHotelInfoRequestDTO;
import com.example.hotel.dto.request.DeleteRoomTypeRequestDTO;
import com.example.hotel.dto.request.ModifyHotelInfoRequestDTO;
import com.example.hotel.dto.request.ModifyRoomTypeInfoRequestDTO;
import com.example.hotel.dto.request.QueryHotelRequestDTO;
import com.example.hotel.dto.request.QueryRoomTypePriceRequestDTO;
import com.example.hotel.dto.request.QueryRoomTypeRequestDTO;
import com.example.hotel.dto.response.AvailableHotelResponse;
import com.example.hotel.dto.response.HotelDetailResponse;
import com.example.hotel.dto.response.RoomAndTypeWithPriceResponse;
import com.example.hotel.service.roomType.RoomTypeInfoService;
import com.example.hotel.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/roomType")
@Api(tags = "Room Type API")
@AllArgsConstructor
public class RoomTypeController {


  private final RoomTypeInfoService roomTypeInfoService;

  /**
   * add room type
   *
   * @return
   */
  @PostMapping("/addRoomType")
  public ResponseResult addRoomType(@RequestHeader("Authorization") String token,
      @RequestBody AddRoomTypeRequestDTO requestDTO) {
       log.info("Token is:" + token);
      String msg = roomTypeInfoService.addRoomType(requestDTO);
      return ResponseResult.ofSuccess(msg);
  }

  /**
   * query room type info
   *
   * @return
   */
  @GetMapping("/queryRoomTypeInfo")
  @RequestMapping(value = "queryRoomTypeInfo", method = RequestMethod.GET)
  public ResponseResult<HotelDetailResponse> queryHotelInfo(
      @RequestHeader("Authorization") String token,
      @ApiParam(value = "query room type details ", required = true)
      @RequestBody QueryRoomTypeRequestDTO requestDTO) {
     log.info("I am in query room type info");
    return ResponseResult.ofSuccess(roomTypeInfoService.getRoomOnId(requestDTO.getId()));
  }

  /**
   * modify room type info
   *
   * @return
   */
  @PutMapping("/modifyRoomTypeInfo")
  @RequestMapping(value = "modifyRoomTypeInfo", method = RequestMethod.PUT)
  public ResponseResult modifyHotelInfo(@RequestHeader("Authorization")
      String token,@ApiParam(value = "room type details", required = true)
      @RequestBody ModifyRoomTypeInfoRequestDTO requestDTO) {
      log.info("I am in update room type info");
      String msg = roomTypeInfoService.updateRoomType(requestDTO);
      return ResponseResult.ofSuccess(msg);
  }

  /**
   * query room types(with price) and available room count of a concrete hotel
   *
   * @return
   */
  @PostMapping("/queryRoomAndTypeWithPrice")
  @RequestMapping(value = "queryRoomAndTypeWithPrice", method = RequestMethod.POST)
  public ResponseResult<List<RoomAndTypeWithPriceResponse>> queryRoomAndTypeWithPrice(
      @ApiParam(value = "query price and available room count of each room type", required = true)
      @RequestBody QueryRoomTypePriceRequestDTO requestDTO) {

    return ResponseResult.ofSuccess(roomTypeInfoService.getHotelAvailableRoomWithPrice(requestDTO));
  }
}
