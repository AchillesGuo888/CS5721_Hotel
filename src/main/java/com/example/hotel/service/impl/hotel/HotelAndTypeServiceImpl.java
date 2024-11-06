package com.example.hotel.service.impl.hotel;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.example.hotel.common.base.ResponseCode;
import com.example.hotel.common.constant.CommonConstant;
import com.example.hotel.dto.AvailableRoomCountDTO;
import com.example.hotel.dto.request.ForgetPasswordRequestDTO;
import com.example.hotel.dto.request.ModifyUserInfoRequestDTO;
import com.example.hotel.dto.request.PasswordModifyRequestDTO;
import com.example.hotel.dto.request.QueryHotelRequestDTO;
import com.example.hotel.dto.request.RegisterRequestDTO;
import com.example.hotel.dto.request.UserLoginRequestDTO;
import com.example.hotel.dto.response.AvailableHotelResponse;
import com.example.hotel.dto.response.RegisterResponse;
import com.example.hotel.dto.response.UpdateInfoResponse;
import com.example.hotel.dto.response.UserInfoResponse;
import com.example.hotel.entity.HotelInfo;
import com.example.hotel.entity.HotelInfoExample;
import com.example.hotel.entity.User;
import com.example.hotel.entity.UserExample;
import com.example.hotel.enums.MemberShipEnum;
import com.example.hotel.enums.UserTypeEnum;
import com.example.hotel.exception.BizException;
import com.example.hotel.exception.NoRollbackException;
import com.example.hotel.mapper.HotelInfoMapper;
import com.example.hotel.mapper.UserMapper;
import com.example.hotel.service.hotel.HotelAndTypeService;
import com.example.hotel.service.order.OrderAndDetailService;
import com.example.hotel.service.roomType.RoomTypeInfoService;
import com.example.hotel.service.user.UserService;
import com.example.hotel.service.user.auth.AbstractAuth;
import com.example.hotel.service.user.auth.Auth4EmailPasswordMatch;
import com.example.hotel.util.EnumUtil;
import com.example.hotel.util.JwtUtil;
import com.example.hotel.util.MD5Util;
import com.example.hotel.util.ValidateUtils;
import com.example.hotel.util.VerificationCodeUtil;
import com.google.common.base.Strings;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class HotelAndTypeServiceImpl implements HotelAndTypeService {

  private final HotelInfoMapper hotelInfoMapper;
  private final OrderAndDetailService orderAndDetailService;
  private final RoomTypeInfoService roomTypeInfoService;

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
        requestDTO.getEndDate(),requestDTO.getQuantity());
    if (CollectionUtils.isEmpty(hotelRoomTypeList)){
      return response;
    }
    //query room type price which can be book
    //only the room type which has available room needs to be calculated price
    List<Long> roomTypeIds = hotelRoomTypeList.stream().filter(item -> item.getAvailableCount()>0).map(AvailableRoomCountDTO::getRoomTypeId).collect(Collectors.toList());
    if (CollectionUtils.isEmpty(roomTypeIds)){
      return response;
    }
    Map<Long, BigDecimal> typeAndPriceMap = roomTypeInfoService.getAvailableRoomTypeAndPrice(roomTypeIds,requestDTO.getStartDate(),
        requestDTO.getEndDate());
    //assemble data
    response = assembleHotelPrice(hotelRoomTypeList,typeAndPriceMap,hotelInfoList);

    return response;
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
      Integer hotelStatus =0;
      if (hotelRoomTypePriceMap.get(key).compareTo(BigDecimal.ZERO)>0){
        hotelPrice = hotelRoomTypePriceMap.get(key);
      }else{
        hotelStatus =1;
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
