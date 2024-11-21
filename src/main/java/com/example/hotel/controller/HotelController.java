package com.example.hotel.controller;


import com.example.hotel.common.base.ResponseResult;
import com.example.hotel.dto.request.AddHotelRequestDTO;
import com.example.hotel.dto.request.DeleteHotelInfoRequestDTO;
import com.example.hotel.dto.request.ModifyHotelInfoRequestDTO;
import com.example.hotel.dto.request.ModifyUserInfoRequestDTO;
import com.example.hotel.dto.request.QueryHotelRequestDTO;
import com.example.hotel.dto.response.AvailableHotelResponse;
import com.example.hotel.dto.response.HotelDetailResponse;
import com.example.hotel.entity.Hotel;
import com.example.hotel.mapper.HotelMapper;
import com.example.hotel.mapper.OrderMapper;
import com.example.hotel.service.hotel.HotelService;
import com.example.hotel.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import java.util.List;
import java.util.stream.Collectors;
import javax.management.Query;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/hotel")
@Api(tags = "Hotel API")
public class HotelController {

  @Autowired
  private HotelService hotelService;

  /**
   * add hotel
   *
   * @return
   */
  @PostMapping("/add")
  public ResponseResult addHotel(@RequestHeader("Authorization") String token,
                                 @ApiParam(value = "Hotel details", required = true)
                                 @RequestBody AddHotelRequestDTO addHotelRequestDTO) {
    return hotelService.addHotel(addHotelRequestDTO);
  }

  /**
   * query Hotel info by id
   *
   * @return
   */
  @PostMapping("/queryHotelInfo")
  public ResponseResult<HotelDetailResponse> queryHotelInfo(
          @RequestHeader("Authorization") String token,
          @ApiParam(value = "query hotel details", required = true)
          @RequestBody QueryHotelRequestDTO queryHotelRequestDTO) {
    return hotelService.queryHotelInfo(queryHotelRequestDTO);
  }

  /**
   * modify hotel info
   *
   * @return
   */
  @PutMapping("/modifyHotelInfo")
  public ResponseResult modifyHotelInfo(@RequestHeader("Authorization") String token,
                                        @ApiParam(value = "Hotel details", required = true)
                                        @RequestBody ModifyHotelInfoRequestDTO modifyHotelInfoRequestDTO) {
    return hotelService.modifyHotelInfo(modifyHotelInfoRequestDTO);
  }

  /**
   * delete hotel info
   *
   * @return
   */
  @DeleteMapping("/deleteHotel")
  public ResponseResult deleteHotel(@RequestHeader("Authorization") String token,
                                    @ApiParam(value = "delete hotel", required = true)
                                    @RequestBody DeleteHotelInfoRequestDTO deleteHotelInfoRequestDTO) {
    return hotelService.deleteHotel(deleteHotelInfoRequestDTO);
  }

  /**
   * query hotel list by conditions
   *
   * @return
   */
  @PostMapping("/queryHotelList")
  public ResponseResult<List<AvailableHotelResponse>> queryHotelList(
          @RequestHeader("Authorization") String token,
          @ApiParam(value = "query hotel details", required = true)
          @RequestBody QueryHotelRequestDTO queryHotelRequestDTO) {
    return hotelService.queryHotelList(queryHotelRequestDTO);
  }
}
