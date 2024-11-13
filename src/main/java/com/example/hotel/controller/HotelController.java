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
import com.example.hotel.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import java.util.List;
import java.util.stream.Collectors;
import javax.management.Query;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Insert;
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
  private HotelMapper HotelMapper;
  private OrderMapper orderMapper;

  /**
   * add hotel
   *
   * @return
   */
  @PostMapping("/add")
  @RequestMapping(value = "addHotel", method = RequestMethod.POST)
  public ResponseResult addHotel(@RequestHeader("Authorization") String token, @ApiParam(value = "Hotel details", required = true) @RequestBody AddHotelRequestDTO addHotelRequestDTO) {
    Hotel hotel = new Hotel();
    hotel.setName(addHotelRequestDTO.getName());
    hotel.setAddress(addHotelRequestDTO.getAddress());
    hotel.setCity(addHotelRequestDTO.getCity());
    hotel.setState(addHotelRequestDTO.getState());
    hotel.setCountry(addHotelRequestDTO.getCountry());
    hotel.setZipCode(addHotelRequestDTO.getZipCode());
    hotel.setFacilities(addHotelRequestDTO.getFacilities());
    hotel.setTotalRooms(addHotelRequestDTO.getTotalRooms());
    hotel.setPhoneNumber(addHotelRequestDTO.getPhoneNumber());

    //Insert hotel information into database
    int rowsAffected = HotelMapper.addHotel(hotel);
    if (rowsAffected > 0) {
      return ResponseResult.ofSuccess(hotel);
    } else {
      return ResponseResult.ofError(500L, "Failed to add hotel");
    }
  }

  /**
   * query Hotel info by id
   *
   * @return
   */
  @PostMapping("/queryHotelInfo")
  @RequestMapping(value = "queryHotelInfo", method = RequestMethod.POST)
  public ResponseResult<HotelDetailResponse> queryHotelInfo(
      @RequestHeader("Authorization") String token,
      @ApiParam(value = "query hotel details ", required = true)
      @RequestBody QueryHotelRequestDTO queryHotelRequestDTO) {
    //get hotelId
    Long hotelId = queryHotelRequestDTO.getHotelId();
    Hotel hotel = HotelMapper.findHotelById(hotelId);
    //check
    if(hotel == null) {
      return ResponseResult.ofError(404L,"Hotel not found");
    }

    HotelDetailResponse response = HotelDetailResponse.builder()
            .name(hotel.getName())
            .address(hotel.getAddress())
            .city(hotel.getCity())
            .state(hotel.getState())
            .country(hotel.getCountry())
            .zipCode(hotel.getZipCode())
            .facilities(hotel.getFacilities())
            .totalRooms(hotel.getTotalRooms())
            .phoneNumber(hotel.getPhoneNumber())
            .build();

    return ResponseResult.ofSuccess(response);
  }

  /**
   * modify hotel info
   *
   * @return
   */
  @PutMapping("/modifyHotelInfo")
  @RequestMapping(value = "modifyHotelInfo", method = RequestMethod.PUT)
  public ResponseResult modifyHotelInfo(@RequestHeader("Authorization") String token,@ApiParam(value = "Hotel details", required = true) @RequestBody ModifyHotelInfoRequestDTO modifyHotelInfoRequestDTOInfo) {
    //get Id
    Long hotelId = modifyHotelInfoRequestDTOInfo.getHotelId();
    //search hotel information
    Hotel hotel = HotelMapper.findHotelById(hotelId);
    //check
    if(hotel == null){
      return ResponseResult.ofError(404L,"Hotel not found");
    }

    //update hotel information
    hotel.setName(modifyHotelInfoRequestDTOInfo.getName());
    hotel.setAddress(modifyHotelInfoRequestDTOInfo.getAddress());
    hotel.setCity(modifyHotelInfoRequestDTOInfo.getCity());
    hotel.setState(modifyHotelInfoRequestDTOInfo.getState());
    hotel.setCountry(modifyHotelInfoRequestDTOInfo.getCountry());
    hotel.setZipCode(modifyHotelInfoRequestDTOInfo.getZipCode());
    hotel.setFacilities(modifyHotelInfoRequestDTOInfo.getFacilities());
    hotel.setTotalRooms(modifyHotelInfoRequestDTOInfo.getTotalRooms());
    hotel.setPhoneNumber(modifyHotelInfoRequestDTOInfo.getPhoneNumber());

    HotelMapper.modifyHotel(hotel);

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
    //get Id
    Long hotelId = deleteHotelInfoRequestDTO.getHotelId();

    //check if null
    if(hotelId == null){
      return ResponseResult.ofError(400L,"Hotel Id is required");
    }

    //check if there are any reservations associated with this hotel
    int orderCount = orderMapper.findOrdersByHotelId(hotelId);
    if(orderCount > 0){
      return ResponseResult.ofError(403L,"Cannot delete hotel with existing orders");
    }

    //delete hotel
    int deletedRows = HotelMapper.deleteHotelById(hotelId);

    //check result
    if(deletedRows > 0){
      return ResponseResult.ofSuccess();
    }else{
      return ResponseResult.ofError(404L,"Hotel not found or could not be deleted");
    }
  }

  /**
   * query hotel list by conditions
   *
   * @return
   */
  @PostMapping("/queryHotelList")
  @RequestMapping(value = "queryHotelList", method = RequestMethod.POST)
  public ResponseResult<List<AvailableHotelResponse>> queryUserInfo(@RequestHeader("Authorization") String token,@ApiParam(value = "query hotel details ", required = true) @RequestBody QueryHotelRequestDTO queryHotelRequestDTO) {

    //Get query conditions
    String name = queryHotelRequestDTO.getName();
    String address = queryHotelRequestDTO.getAddress();
    String city = queryHotelRequestDTO.getCity();
    String state = queryHotelRequestDTO.getState();
    String country = queryHotelRequestDTO.getCountry();
    String zipCode = queryHotelRequestDTO.getZipCode();

    //page
    int page = queryHotelRequestDTO.getPage();
    int size = queryHotelRequestDTO.getSize();
    int offset = (page - 1) * size;

    //Search for hotels that meet criteria
    List<Hotel> hotels = HotelMapper.findHotelsByConditions(name,address, city, state, country, zipCode, offset, size);

    //check
    if(hotels == null || hotels.isEmpty()){
      return ResponseResult.ofError(404L, "No hotels found matching the given criteria");
    }

    List<HotelDetailResponse> response = hotels.stream()
            .map(hotel -> HotelDetailResponse.builder()
                    .name(hotel.getName())
                    .address(hotel.getAddress())
                    .city(hotel.getCity())
                    .state(hotel.getState())
                    .country(hotel.getCountry())
                    .zipCode(hotel.getZipCode())
                    .facilities(hotel.getFacilities())
                    .totalRooms(hotel.getTotalRooms())
                    .phoneNumber(hotel.getPhoneNumber())
                    .build())
            .collect(Collectors.toList());

    return ResponseResult.ofSuccess();
  }
}
