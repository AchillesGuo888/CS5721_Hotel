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
import com.example.hotel.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/roomType")
@Api(tags = "Room Type API")
public class RoomTypeController {


  @Autowired
  private JwtUtil jwtUtil;

  /**
   * add room type
   *
   * @return
   */
  @PostMapping("/add")
  @RequestMapping(value = "addRoomType", method = RequestMethod.POST)
  public ResponseResult userLogout(@RequestHeader("Authorization") String token,
      @ApiParam(value = "Room type details", required = true)
      @RequestBody AddRoomTypeRequestDTO requestDTO) {
      return ResponseResult.ofSuccess();
  }

  /**
   * query room type info
   *
   * @return
   */
  @PostMapping("/queryRoomTypeInfo")
  @RequestMapping(value = "queryRoomTypeInfo", method = RequestMethod.POST)
  public ResponseResult<HotelDetailResponse> queryHotelInfo(
      @RequestHeader("Authorization") String token,
      @ApiParam(value = "query room type details ", required = true)
      @RequestBody QueryRoomTypeRequestDTO requestDTO) {

    return ResponseResult.ofSuccess();
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

    return ResponseResult.ofSuccess();
  }

  /**
   * delete room type info
   *
   * @return
   */
  @DeleteMapping("/deleteRoomType")
  @RequestMapping(value = "deleteRoomType", method = RequestMethod.DELETE)
  public ResponseResult deleteRoomType(@RequestHeader("Authorization")
      String token,@ApiParam(value = "delete room type", required = true)
      @RequestBody DeleteRoomTypeRequestDTO requestDTO) {

    return ResponseResult.ofSuccess();
  }

  /**
   * query room types(with price) and available room count of a concrete hotel
   *
   * @return
   */
  @PostMapping("/queryRoomAndTypeWithPrice")
  @RequestMapping(value = "queryRoomAndTypeWithPrice", method = RequestMethod.POST)
  public ResponseResult<List<RoomAndTypeWithPriceResponse>> queryRoomAndTypeWithPrice(
      @RequestHeader("Authorization") String token,
      @ApiParam(value = "query price and available room count of each room type", required = true)
      @RequestBody QueryRoomTypePriceRequestDTO requestDTO) {

    return ResponseResult.ofSuccess();
  }
}
