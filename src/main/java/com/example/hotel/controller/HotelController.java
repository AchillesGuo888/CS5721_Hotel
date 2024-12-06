package com.example.hotel.controller;

import com.example.hotel.common.base.ResponseCode;
import com.example.hotel.common.base.ResponseResult;
import com.example.hotel.dto.request.AddHotelRequestDTO;
import com.example.hotel.dto.request.DeleteHotelInfoRequestDTO;
import com.example.hotel.dto.request.ModifyHotelInfoRequestDTO;
import com.example.hotel.dto.request.QueryHotelRequestDTO;
import com.example.hotel.dto.request.QueryRoomTypePriceRequestDTO;
import com.example.hotel.dto.response.AddHotelResponse;
import com.example.hotel.dto.response.AvailableHotelResponse;
import com.example.hotel.dto.response.HotelDetailResponse;
import com.example.hotel.dto.response.RoomAndTypeWithPriceResponse;
import com.example.hotel.entity.HotelInfo;
import com.example.hotel.exception.BizException;
import com.example.hotel.service.hotel.HotelInfoService;
import com.example.hotel.service.hotel.HotelService;
import io.micrometer.core.instrument.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.awt.print.Pageable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

@Slf4j
@RestController
@RequestMapping("/hotel")
@Api(tags = "Hotel API")
@AllArgsConstructor
public class HotelController {

  private final HotelService hotelService;

  /**
   * add Hotel
   *
   * @return
   */
  @ApiOperation(value = "Add a new hotel", notes = "添加一个新的酒店")
  @PostMapping("/addHotel")
  public ResponseResult addHotel(@RequestHeader("Authorization") String token,
                                 @ApiParam(value = "Hotel details", required = true) @RequestBody AddHotelRequestDTO addHotelRequestDTO) {
    try {
      // 调用 Service 层的添加酒店方法
      AddHotelResponse response = hotelService.addHotel(addHotelRequestDTO);

      // 返回成功结果
      return ResponseResult.ofSuccess(response);
    } catch (Exception e) {
      // 捕获异常并返回失败的响应
      return ResponseResult.ofError(ResponseCode.server_err.getCode(), "添加酒店失败: " + e.getMessage());
    }
  }

  /**
   * query Hotel list by info
   *
   * @return
   */
  @ApiOperation(value = "Query Hotel info", notes = "查询酒店信息")
  @PostMapping("/queryHotelInfo")
  public ResponseEntity<Page<HotelDetailResponse>> queryHotelInfo(
          @RequestBody QueryHotelRequestDTO queryHotelRequestDTO,
          @RequestParam(defaultValue = "0") int page,
          @RequestParam(defaultValue = "10") int size) {
    try {
      Page<HotelDetailResponse> response = HotelInfoService.queryHotelInfo(queryHotelRequestDTO, page, size);
      return ResponseEntity.ok(response);
    } catch (BizException e) {
      return ResponseEntity.status(500).body(null); // 错误处理
    }
  }

  /**
   * query Hotel by id
   *
   * @return
   */
  @Autowired
  private HotelInfoService hotelInfoService;

  @GetMapping("/{id}")
  public ResponseEntity<?> getHotelById(@PathVariable Long id) {
    HotelInfo hotelInfo = hotelService.getHotelById(id);
    if (hotelInfo == null) {
      return ResponseEntity.status(404).body("Hotel not found");
    }
    return ResponseEntity.ok(hotelInfo);
  }

  /**
   * modify hotel info
   *
   * @return
   */
  @RestController
  @RequestMapping("/hotel")
  public class HotelInfoController {
    @Autowired
    private HotelService hotelService;

    @PutMapping("/modifyHotelInfo")
    public ResponseResult modifyHotelInfo(
            @RequestHeader("Authorization") String token,
            @ApiParam(value = "Hotel details", required = true)
            @RequestBody ModifyHotelInfoRequestDTO modifyHotelInfoRequestDTOInfo) {
      hotelService.modifyHotelInfo(modifyHotelInfoRequestDTOInfo);
      return ResponseResult.ofSuccess("Hotel info updated successfully");
    }
  }

  /**
   * Delete hotel info
   *
   *
   * @param deleteHotelInfoRequestDTO DTO containing the ID of the hotel to be deleted
   * @return ResponseResult indicating success or failure
   */
  @DeleteMapping("/deleteHotel")
  public ResponseResult deleteHotel(
          @RequestHeader("Authorization") String token,
          @ApiParam(value = "Delete hotel", required = true) @RequestBody DeleteHotelInfoRequestDTO deleteHotelInfoRequestDTO) {
    hotelService.deleteHotel(deleteHotelInfoRequestDTO.getId());
    return ResponseResult.ofSuccess("Hotel deleted successfully");
  }

}
