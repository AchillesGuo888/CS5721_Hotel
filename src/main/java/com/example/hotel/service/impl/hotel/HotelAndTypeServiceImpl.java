package com.example.hotel.service.impl.hotel;

import com.example.hotel.dto.AvailableRoomCountDTO;
import com.example.hotel.dto.request.QueryHotelRequestDTO;
import com.example.hotel.dto.request.QueryRoomTypePriceRequestDTO;
import com.example.hotel.dto.response.AvailableHotelResponse;
import com.example.hotel.dto.response.RoomAndTypeWithPriceResponse;

import com.example.hotel.entity.HotelInfo;
import com.example.hotel.entity.HotelInfoExample;
import com.example.hotel.enums.HotelOrRoomTypeStatusEnum;
import com.example.hotel.mapper.HotelInfoMapper;
import com.example.hotel.service.hotel.HotelAndTypeService;
import com.example.hotel.service.order.OrderAndDetailService;
import com.example.hotel.service.roomType.RoomTypePriceService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class HotelAndTypeServiceImpl implements HotelAndTypeService {

  private final HotelInfoMapper hotelInfoMapper;
  private final OrderAndDetailService orderAndDetailService;
  private final RoomTypePriceService roomTypePriceService;

  @Override
  public List<AvailableHotelResponse> queryHotelListWithPrice(QueryHotelRequestDTO requestDTO) {
    //Query the hotel list according to the conditions
    List<HotelInfo> hotelInfoList = getHotelList(requestDTO.getName(),requestDTO.getAddress(),requestDTO.getCity());
    List<AvailableHotelResponse> response= new ArrayList<>();
    if (CollectionUtils.isEmpty(hotelInfoList)){
      return response;
    }
    List<Long> hotelIds = hotelInfoList.stream()
        .map(item -> item.getId())
        .collect(Collectors.toList());

    //query order and room reservation in this period
    List<AvailableRoomCountDTO> hotelRoomTypeList =
        orderAndDetailService.queryAvailableRoomType(hotelIds,
            requestDTO.getStartDate(),
        requestDTO.getEndDate(),requestDTO.getQuantity(),null);
    if (CollectionUtils.isEmpty(hotelRoomTypeList)){
      return response;
    }
    //query room type price which can be book
    //only the room type which has available room needs to be calculated price
    List<Long> roomTypeIds = hotelRoomTypeList.stream()
        .filter(item -> item.getAvailableCount()>0)
        .map(AvailableRoomCountDTO::getRoomTypeId)
        .collect(Collectors.toList());
    if (CollectionUtils.isEmpty(roomTypeIds)){
      //room type ids list is empty, it means hotels are all full
      return getAllHotelFullResult(hotelInfoList);
    }
    //Map<roomTypeId,price>
    Map<Long, BigDecimal> typeAndPriceMap = roomTypePriceService.getHotelsRoomTypeAndPrice(roomTypeIds,requestDTO.getStartDate(),
        requestDTO.getEndDate());
    //assemble data
    response = assembleHotelPrice(hotelRoomTypeList,typeAndPriceMap,hotelInfoList);

    return response;
  }

  @Override
  public List<RoomAndTypeWithPriceResponse> getHotelAvailableRoomWithPrice(
      QueryRoomTypePriceRequestDTO requestDTO) {

    //get hotel's room type info
    List<Long> hotelIds = new ArrayList<>();
    hotelIds.add(requestDTO.getHotelId());
    List<AvailableRoomCountDTO> roomTypeInfoList =
        orderAndDetailService.queryAvailableRoomType(hotelIds,
            requestDTO.getStartDate(),
            requestDTO.getEndDate(),requestDTO.getQuantity(),null);

    //get room type and price map
    List<Long> roomTypeIds = roomTypeInfoList.stream()
        .filter(item -> item.getAvailableCount()>0)
        .map(AvailableRoomCountDTO::getRoomTypeId)
        .collect(Collectors.toList());
    if (CollectionUtils.isEmpty(roomTypeIds)){
      //room type ids list is empty, it means the hotel is all full
      return getAllRoomTypeFullResult(roomTypeInfoList);
    }
    //Map<roomTypeId,price>
    Map<Long, BigDecimal> typeAndPriceMap = roomTypePriceService.getHotelsRoomTypeAndPrice(roomTypeIds
        ,requestDTO.getStartDate(),
        requestDTO.getEndDate());

    //assemble data
    List<RoomAndTypeWithPriceResponse> response= assembleRoomTypePrice(roomTypeInfoList,typeAndPriceMap);

    return response;
  }

  //
  private List<RoomAndTypeWithPriceResponse> assembleRoomTypePrice(List<AvailableRoomCountDTO> roomTypeInfoList, Map<Long, BigDecimal> typeAndPriceMap) {

    List<RoomAndTypeWithPriceResponse> result = new ArrayList<>();
    for (AvailableRoomCountDTO item:roomTypeInfoList){
      RoomAndTypeWithPriceResponse typeInfo = RoomAndTypeWithPriceResponse.builder().build();
      typeInfo.setRoomTypeId(item.getRoomTypeId());
      typeInfo.setRoomTypeName(item.getRoomTypeName());
      typeInfo.setAvailableCount(item.getAvailableCount());
      typeInfo.setPrice(typeAndPriceMap.get(item.getRoomTypeId()));
      if (item.getAvailableCount()==0){
        typeInfo.setStatus(HotelOrRoomTypeStatusEnum.FULL.getCode());
      }else{
        typeInfo.setStatus(HotelOrRoomTypeStatusEnum.AVAILABLE.getCode());
      }
      result.add(typeInfo);
    }

    return result;
  }

  private List<RoomAndTypeWithPriceResponse> getAllRoomTypeFullResult(
      List<AvailableRoomCountDTO> roomTypeInfoList) {

    List<RoomAndTypeWithPriceResponse> resultList = roomTypeInfoList.stream()
        .map(roomType -> RoomAndTypeWithPriceResponse.builder()
                .roomTypeId(roomType.getRoomTypeId())
                .roomTypeName(roomType.getRoomTypeName())
                .availableCount(0)
                .status(HotelOrRoomTypeStatusEnum.FULL.getCode())
                .price(null).build()
            )
        .collect(Collectors.toList());
    return resultList;
  }

  private List<AvailableHotelResponse> getAllHotelFullResult(List<HotelInfo> hotelInfoList) {
    //status=1, it means the hotel is full
    List<AvailableHotelResponse> resultList = hotelInfoList.stream()
        .map(hotel -> AvailableHotelResponse.builder()
            .hotelId(hotel.getId()).hotelName(hotel.getHotelName())
            .status(HotelOrRoomTypeStatusEnum.FULL.getCode()).price(null).build())
        .collect(Collectors.toList());
    return resultList;
  }

  private List<AvailableHotelResponse> assembleHotelPrice(
      List<AvailableRoomCountDTO> hotelRoomTypeList, Map<Long, BigDecimal> typeAndPriceMap,
      List<HotelInfo> hotelInfoList) {

    List<AvailableHotelResponse> result = new ArrayList<>();

    Map<Long,BigDecimal> hotelRoomTypePriceMap = new HashMap<>();

    //get the lowest price of each hotel
    for (AvailableRoomCountDTO item: hotelRoomTypeList){
      hotelRoomTypePriceMap.putIfAbsent(item.getHotelId(),new BigDecimal(0.00));
      if (item.getAvailableCount()==0){
        //this type has no room to be booked
        continue;
      }
      if (hotelRoomTypePriceMap.get(item.getHotelId()).compareTo(BigDecimal.ZERO)==0){
        hotelRoomTypePriceMap.put(item.getHotelId(),typeAndPriceMap.get(item.getRoomTypeId()));
      }else {
        if (hotelRoomTypePriceMap.get(item.getHotelId()).compareTo(typeAndPriceMap.get(item.getRoomTypeId()))>0){
          hotelRoomTypePriceMap.put(item.getHotelId(),typeAndPriceMap.get(item.getRoomTypeId()));
        }
      }
    }
    Map<Long, HotelInfo> hotelMap = hotelInfoList.stream()
        .collect(Collectors.toMap(HotelInfo::getId, hotel -> hotel));
    hotelMap.forEach((key,value) ->{
      BigDecimal hotelPrice = new BigDecimal(0.00);
      Byte hotelStatus =HotelOrRoomTypeStatusEnum.AVAILABLE.getCode();
      if (hotelRoomTypePriceMap.get(key).compareTo(BigDecimal.ZERO)>0){
        hotelPrice = hotelRoomTypePriceMap.get(key);
      }else{
        hotelStatus =HotelOrRoomTypeStatusEnum.FULL.getCode();
      }

      AvailableHotelResponse obj = AvailableHotelResponse.builder()
          .hotelId(value.getId()).hotelName(value.getHotelName()).price(hotelPrice).status(hotelStatus)
          .build();
      result.add(obj);
    });


    return result;
  }

  private List<HotelInfo> getHotelList(String name, String address, String city) {
    HotelInfoExample example = new HotelInfoExample();
    HotelInfoExample.Criteria criteria = example.createCriteria();
    criteria.andCityEqualTo(city);
    if (StringUtils.isNotBlank(address)){
      criteria.andAddressLike("%"+address+"%");
    }
    if (StringUtils.isNotBlank(name)){
      criteria.andHotelNameLike("%"+name+"%");
    }
    return hotelInfoMapper.selectByExample(example);
  }
}
