package com.example.hotel.controller;


import com.example.hotel.service.roomType.RoomTypePriceService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
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
