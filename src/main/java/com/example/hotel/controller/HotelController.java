package com.example.hotel.controller;


import com.example.hotel.common.base.ResponseResult;
import com.example.hotel.dto.request.AddHotelRequestDTO;
import com.example.hotel.dto.request.DeleteHotelInfoRequestDTO;
import com.example.hotel.dto.request.ModifyHotelInfoRequestDTO;
import com.example.hotel.dto.request.ModifyUserInfoRequestDTO;
import com.example.hotel.dto.request.QueryHotelRequestDTO;
import com.example.hotel.dto.response.AvailableHotelResponse;
import com.example.hotel.dto.response.HotelDetailResponse;
import com.example.hotel.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import java.util.List;
import javax.management.Query;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
@RequestMapping("/hotel")
@Api(tags = "Hotel API")
public class HotelController {


  @Autowired
  private JwtUtil jwtUtil;

  /**
   * add hotel
   *
   * @return
   */
  @PostMapping("/add")
  @RequestMapping(value = "addHotel", method = RequestMethod.POST)
  public ResponseResult addHotel(@RequestHeader("Authorization") String token, @ApiParam(value = "Hotel details", required = true) @RequestBody AddHotelRequestDTO addHotelRequestDTO) {
      return ResponseResult.ofSuccess();
  }

  /**
   * query Hotel info
   *
   * @return
   */
  @PostMapping("/queryHotelInfo")
  @RequestMapping(value = "queryHotelInfo", method = RequestMethod.POST)
  public ResponseResult<HotelDetailResponse> queryHotelInfo(
      @RequestHeader("Authorization") String token,
      @ApiParam(value = "query hotel details ", required = true)
      @RequestBody QueryHotelRequestDTO queryHotelRequestDTO) {

    return ResponseResult.ofSuccess();
  }

  /**
   * modify hotel info
   *
   * @return
   */
  @PutMapping("/modifyHotelInfo")
  @RequestMapping(value = "modifyHotelInfo", method = RequestMethod.PUT)
  public ResponseResult modifyHotelInfo(@RequestHeader("Authorization") String token,@ApiParam(value = "Hotel details", required = true) @RequestBody ModifyHotelInfoRequestDTO modifyHotelInfoRequestDTOInfo) {

    return ResponseResult.ofSuccess();
  }

  /**
   * delete hotel info
   *
   * @return
   */
  @DeleteMapping("/deleteHotel")
  @RequestMapping(value = "deleteHotel", method = RequestMethod.DELETE)
  public ResponseResult deleteHotel(@RequestHeader("Authorization") String token,@ApiParam(value = "delete hotel", required = true) @RequestBody DeleteHotelInfoRequestDTO deleteHotelInfoRequestDTO) {

    return ResponseResult.ofSuccess();
  }

  /**
   * query hotel list(with price)
   *
   * @return
   */
  @PostMapping("/queryHotelList")
  @RequestMapping(value = "queryHotelList", method = RequestMethod.POST)
  public ResponseResult<List<AvailableHotelResponse>> queryUserInfo(@RequestHeader("Authorization") String token,@ApiParam(value = "query hotel details ", required = true) @RequestBody QueryHotelRequestDTO queryHotelRequestDTO) {
    return ResponseResult.ofSuccess();
  }


}
