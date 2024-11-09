package com.example.hotel.service.impl.roomType;

import com.example.hotel.dto.request.QueryRoomTypePriceRequestDTO;

import com.example.hotel.dto.response.RoomAndTypeWithPriceResponse;
import com.example.hotel.entity.RoomTypeInfo;
import com.example.hotel.entity.RoomTypeInfoExample;

import com.example.hotel.mapper.RoomTypeInfoMapper;

import com.example.hotel.service.roomType.RoomTypeInfoService;

import java.math.BigDecimal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class RoomTypeInfoServiceImpl implements RoomTypeInfoService {

  private final RoomTypeInfoMapper roomTypeInfoMapper;

  @Override
  public List<RoomAndTypeWithPriceResponse> getHotelAvailableRoomWithPrice(
      QueryRoomTypePriceRequestDTO requestDTO) {
//    roomTypeInfoMapper
    return null;
  }




  @Override
  public Map<Long, Map<String, BigDecimal>> getDefaultPrices(List<Long> roomTypeIds) {
    RoomTypeInfoExample example = new RoomTypeInfoExample();
    RoomTypeInfoExample.Criteria criteria = example.createCriteria();
    criteria.andIdIn(roomTypeIds);
    List<RoomTypeInfo> roomTypeList = roomTypeInfoMapper.selectByExample(example);

    Map<Long, Map<String, BigDecimal>> defaultPriceMap = new HashMap<>();
    for (RoomTypeInfo item: roomTypeList){
      Map<String, BigDecimal> defaultPrices = new HashMap<>();
      defaultPrices.put("weekday", item.getWeekdayPrice());
      defaultPrices.put("weekend", item.getWeekendPrice());
      defaultPriceMap.put(item.getId(), defaultPrices);
    }
    return defaultPriceMap;
  }
}
