package com.example.hotel.controller;


import com.example.hotel.common.base.ResponseResult;
import com.example.hotel.dto.request.AddHotelRequestDTO;
import com.example.hotel.dto.request.AddRoomRequestDTO;
import com.example.hotel.dto.request.CheckInRequestDTO;
import com.example.hotel.dto.request.DeleteHotelInfoRequestDTO;
import com.example.hotel.dto.request.ModifyHotelInfoRequestDTO;
import com.example.hotel.dto.request.QueryHotelRequestDTO;
import com.example.hotel.dto.request.QueryRoomListRequestDTO;
import com.example.hotel.dto.request.QueryRoomRequestDTO;
import com.example.hotel.dto.response.AvailableHotelResponse;
import com.example.hotel.dto.response.CheckInResponse;
import com.example.hotel.dto.response.HotelDetailResponse;
import com.example.hotel.dto.response.RoomDetailResponse;
import com.example.hotel.service.room.RoomInfoService;
import com.example.hotel.exception.BizException;
import com.example.hotel.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/room")
@Api(tags = "Room API")
@AllArgsConstructor
public class RoomController {

  private final RoomInfoService roomInfoService;
  @Autowired
  private JwtUtil jwtUtil;

  /**
   * add room
   *
   * @return
   */
  @PostMapping("/addRoom")
  @RequestMapping(value = "addRoom", method = RequestMethod.POST)
  public ResponseResult addRoom(@RequestHeader("Authorization") String token,
      @ApiParam(value = "room details", required = true)
      @RequestBody AddRoomRequestDTO requestDTO) {
      try {
        roomInfoService.addRoom(requestDTO);
        return ResponseResult.ofSuccess();
      } catch (BizException e) {
        log.error("user login error", e);
        return ResponseResult.ofError(e.getCode().getCode(), e.getMessage());
      }
  }

  /**
   * query room info
   *
   * @return
   */
  @PostMapping("/queryRoomInfo")
  @RequestMapping(value = "queryRoomInfo", method = RequestMethod.POST)
  public ResponseResult<RoomDetailResponse> queryHotelInfo(
      @RequestHeader("Authorization") String token,
      @ApiParam(value = "query room details ", required = true)
      @RequestBody QueryRoomRequestDTO requestDTO) {

    return ResponseResult.ofSuccess();
  }

  /**
   * modify hotel info
   *
   * @return
   */
  @PutMapping("/modifyRoomInfo")
  @RequestMapping(value = "modifyRoomInfo", method = RequestMethod.PUT)
  public ResponseResult modifyHotelInfo(
      @RequestHeader("Authorization") String token,
      @ApiParam(value = "room details", required = true)
      @RequestBody ModifyHotelInfoRequestDTO requestDTO) {

    return ResponseResult.ofSuccess();
  }

  /**
   * delete hotel info
   *
   * @return
   */
  @DeleteMapping("/deleteRoom")
  @RequestMapping(value = "deleteRoom", method = RequestMethod.DELETE)
  public ResponseResult deleteHotel(@RequestHeader("Authorization")
      String token,@ApiParam(value = "delete room", required = true)
  @RequestBody DeleteHotelInfoRequestDTO requestDTO) {

    return ResponseResult.ofSuccess();
  }

  /**
   * query room list
   *
   * @return
   */
  @PostMapping("/queryRoomList")
  @RequestMapping(value = "queryRoomList", method = RequestMethod.POST)
  public ResponseResult<List<RoomDetailResponse>> queryUserInfo(@RequestHeader("Authorization") String token,
      @ApiParam(value = "query room details ", required = true)
      @RequestBody QueryRoomListRequestDTO requestDTO) {
    return ResponseResult.ofSuccess();
  }

  /**
   * check in
   *
   * @return
   */
  @PostMapping("/checkInRoom")
  @RequestMapping(value = "checkInRoom", method = RequestMethod.POST)
  public ResponseResult<CheckInResponse> checkInRoom(@RequestHeader("Authorization") String token,
      @ApiParam(value = "check in room", required = true)
      @RequestBody CheckInRequestDTO requestDTO) {
    return ResponseResult.ofSuccess();
  }


}
