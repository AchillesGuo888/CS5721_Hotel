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
  @PostMapping("/add")
  public ResponseResult addRoomType(
      @RequestBody AddRoomTypeRequestDTO requestDTO) {
      String msg = roomTypeInfoService.addRoomType(requestDTO);
      return ResponseResult.ofSuccess(msg);
  }

  /**
   * query room type info
   *
   * @return
   */
  @GetMapping("/query")
  public ResponseResult<HotelDetailResponse> queryHotelInfo(
      @ApiParam(value = "query room type details ", required = true)
      @RequestBody QueryRoomTypeRequestDTO requestDTO) {
    return ResponseResult.ofSuccess(roomTypeInfoService.getRoomOnId(requestDTO.getId()));
  }

  /**
   * modify room type info
   *
   * @return
   */
  @PutMapping("/modify")
  public ResponseResult modifyHotelInfo(
      @ApiParam(value = "room type details", required = true)
      @RequestBody ModifyRoomTypeInfoRequestDTO requestDTO) {
      String msg = roomTypeInfoService.updateRoomType(requestDTO);
      return ResponseResult.ofSuccess(msg);
  }

}
