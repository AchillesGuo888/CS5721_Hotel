package com.example.hotel.controller;

import com.example.hotel.common.base.ResponseCode;
import com.example.hotel.common.base.ResponseResult;
import com.example.hotel.dto.request.AddHotelRequestDTO;
import com.example.hotel.dto.request.DeleteHotelInfoRequestDTO;
import com.example.hotel.dto.request.ModifyHotelInfoRequestDTO;
import com.example.hotel.dto.request.QueryHotelRequestDTO;
import com.example.hotel.dto.response.AddHotelResponse;
import com.example.hotel.dto.response.HotelDetailResponse;
import com.example.hotel.entity.HotelInfo;
import com.example.hotel.exception.BizException;
import com.example.hotel.service.hotel.HotelInfoService;
import com.example.hotel.service.hotel.HotelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/hotel")
@Api(tags = "Hotel API")
@AllArgsConstructor
public class HotelController {

  private final HotelService hotelService;
  private final HotelInfoService hotelInfoService;

  /**
   * add Hotel
   *
   * @return
   */
  @ApiOperation(value = "Add a new hotel", notes = "Add a new hotel")
  @PostMapping("/addHotel")
  public ResponseResult addHotel(@RequestHeader("Authorization") String token,
                                 @ApiParam(value = "Hotel details", required = true) @RequestBody AddHotelRequestDTO addHotelRequestDTO) {
    try {
      // Call the add hotel method of the Service layer
      AddHotelResponse response = hotelService.addHotel(addHotelRequestDTO);

      // Returns successful result
      return ResponseResult.ofSuccess(response);
    } catch (Exception e) {
      // Catch the exception and return a failed response
      return ResponseResult.ofError(ResponseCode.server_err.getCode(), "Failed to add hotel: " + e.getMessage());
    }
  }

  /**
   * query Hotel list by info
   *
   * @return
   */
  @ApiOperation(value = "Query Hotel info", notes = "Query hotel information")
  @PostMapping("/queryHotelInfo")
  public ResponseEntity<Page<HotelDetailResponse>> queryHotelInfo(
          @RequestBody QueryHotelRequestDTO queryHotelRequestDTO,
          @RequestParam(defaultValue = "0") int page,
          @RequestParam(defaultValue = "10") int size) {
    try {
      Page<HotelDetailResponse> response = hotelInfoService.queryHotelInfo(queryHotelRequestDTO, page, size);
      return ResponseEntity.ok(response);
    } catch (BizException e) {
      return ResponseEntity.status(404).body(null);
    }
  }

  /**
   * query Hotel by id
   *
   * @return
   */

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
      HotelInfo hotelInfo = hotelService.getHotelById(modifyHotelInfoRequestDTOInfo.getHotelId());
      if (hotelInfo == null) {
        return ResponseResult.ofError(404L,"Hotel does not exist");
      }
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
    Integer i = hotelService.deleteHotel(deleteHotelInfoRequestDTO.getId());
    if (i ==1){
      return ResponseResult.ofSuccess("Hotel deleted successfully");
    }else {
      return ResponseResult.ofError(ResponseCode.server_err.getCode(), "Hotel delete failed");
    }

  }

}
