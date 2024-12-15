package com.example.hotel.controller;


import com.example.hotel.common.base.ResponseResult;
import com.example.hotel.dto.request.AddRoomRequestDTO;
import com.example.hotel.dto.request.CheckInRequestDTO;
import com.example.hotel.dto.request.DeleteRoomRequestDTO;
import com.example.hotel.dto.request.ModifyRoomInfoRequestDTO;
import com.example.hotel.dto.request.QueryRoomListRequestDTO;
import com.example.hotel.dto.request.QueryRoomRequestDTO;
import com.example.hotel.dto.response.CheckInResponse;
import com.example.hotel.dto.response.RoomDetailResponse;
import com.example.hotel.service.room.RoomInfoService;
import com.example.hotel.exception.BizException;
import com.example.hotel.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
        log.error("adding room error", e);
        return ResponseResult.ofError(e.getCode().getCode(), e.getMessage());
      }
  }

  /**
   * query room info
   *
   * @return
   */
  @GetMapping("/queryRoomInfo")
  @RequestMapping(value = "queryRoomInfo", method = RequestMethod.GET)
  public ResponseResult<RoomDetailResponse> queryRoomInfo(@RequestHeader("Authorization") String token,
      @ApiParam(value = "query room details ", required = true)
      @RequestBody QueryRoomRequestDTO requestDTO) {
      try {
        return ResponseResult.ofSuccess(roomInfoService.queryRoomInfo(requestDTO));
      } catch (BizException e) {
        log.error("adding room error", e);
        return ResponseResult.ofError(e.getCode().getCode(), e.getMessage());
      }
  }

  /**
   * modify hotel info
   *
   * @return
   */
  @PutMapping("/modifyRoomInfo")
  @RequestMapping(value = "modifyRoomInfo", method = RequestMethod.PUT)
  public ResponseResult modifyRoomInfo(@RequestHeader("Authorization") String token,
      @ApiParam(value = "room details", required = true)
      @RequestBody ModifyRoomInfoRequestDTO requestDTO) {
      try {
        roomInfoService.modifyRoomInfo(requestDTO);
        return ResponseResult.ofSuccess();
      } catch (BizException e) {
        log.error("modify error error", e);
        return ResponseResult.ofError(e.getCode().getCode(), e.getMessage());
      }
  }

  /**
   * delete hotel info
   *
   * @return
   */
  @PutMapping("/deleteRoom")
  @RequestMapping(value = "deleteRoom", method = RequestMethod.PUT)
  public ResponseResult deleteRoom(@RequestHeader("Authorization") String token,
    @ApiParam(value = "delete room", required = true)
    @RequestBody DeleteRoomRequestDTO requestDTO) {
    try {
      roomInfoService.deleteRoom(requestDTO);
      return ResponseResult.ofSuccess();
    } catch (BizException e) {
      log.error("modify error error", e);
      return ResponseResult.ofError(e.getCode().getCode(), e.getMessage());
    }
  }

  /**
   * query room list
   *
   * @return
   */
  @GetMapping("/queryRoomList")
  @RequestMapping(value = "queryRoomList", method = RequestMethod.GET)
  public ResponseResult<List<RoomDetailResponse>> queryUserInfo(@RequestHeader("Authorization") String token,
      @ApiParam(value = "query room list details ", required = true)
      @RequestBody QueryRoomListRequestDTO requestDTO) {
      try {
        return ResponseResult.ofSuccess(roomInfoService.queryRoomList(requestDTO));
      } catch (BizException e) {
        log.error("query room list error", e);
        return ResponseResult.ofError(e.getCode().getCode(), e.getMessage());
      }
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
        try {
          roomInfoService.checkInRoom(requestDTO);
          return ResponseResult.ofSuccess();
        } catch (BizException e) {
          log.error("check in error", e);
          return ResponseResult.ofError(e.getCode().getCode(), e.getMessage());
        }
  }


}
