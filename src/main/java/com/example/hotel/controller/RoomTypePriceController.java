package com.example.hotel.controller;


import com.example.hotel.common.base.ResponseResult;
import com.example.hotel.dto.request.AddRoomTypeRequestDTO;
import com.example.hotel.dto.request.DeleteRoomTypeRequestDTO;
import com.example.hotel.dto.request.ModifyRoomTypeInfoRequestDTO;
import com.example.hotel.dto.request.QueryRoomTypePriceRequestDTO;
import com.example.hotel.dto.request.QueryRoomTypeRequestDTO;
import com.example.hotel.dto.response.HotelDetailResponse;
import com.example.hotel.dto.response.RoomAndTypeWithPriceResponse;
import com.example.hotel.service.roomType.RoomTypeInfoService;
import com.example.hotel.service.roomType.RoomTypePriceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/roomTypePrice")
@Api(tags = "Room Type Price API")
@AllArgsConstructor
public class RoomTypePriceController {


  private final RoomTypePriceService roomTypePriceService;

//  /**
//   * add room type
//   *
//   * @return
//   */
//  @PostMapping("/add")
//  @RequestMapping(value = "addRoomType", method = RequestMethod.POST)
//  public ResponseResult userLogout(@RequestHeader("Authorization") String token,
//      @ApiParam(value = "Room type details", required = true)
//      @RequestBody AddRoomTypeRequestDTO requestDTO) {
//      return ResponseResult.ofSuccess();
//  }
//
//  /**
//   * query room type info
//   *
//   * @return
//   */
//  @PostMapping("/queryRoomTypeInfo")
//  @RequestMapping(value = "queryRoomTypeInfo", method = RequestMethod.POST)
//  public ResponseResult<HotelDetailResponse> queryHotelInfo(
//      @RequestHeader("Authorization") String token,
//      @ApiParam(value = "query room type details ", required = true)
//      @RequestBody QueryRoomTypeRequestDTO requestDTO) {
//
//    return ResponseResult.ofSuccess();
//  }
//
//  /**
//   * modify room type info
//   *
//   * @return
//   */
//  @PutMapping("/modifyRoomTypeInfo")
//  @RequestMapping(value = "modifyRoomTypeInfo", method = RequestMethod.PUT)
//  public ResponseResult modifyHotelInfo(@RequestHeader("Authorization")
//      String token,@ApiParam(value = "room type details", required = true)
//      @RequestBody ModifyRoomTypeInfoRequestDTO requestDTO) {
//
//    return ResponseResult.ofSuccess();
//  }

//  /**
//   * delete room type info
//   *
//   * @return
//   */
//  @DeleteMapping("/deleteRoomType")
//  @RequestMapping(value = "deleteRoomType", method = RequestMethod.DELETE)
//  public ResponseResult deleteRoomType(@RequestHeader("Authorization")
//      String token,@ApiParam(value = "delete room type", required = true)
//      @RequestBody DeleteRoomTypeRequestDTO requestDTO) {
//
//    return ResponseResult.ofSuccess();
//  }


}
