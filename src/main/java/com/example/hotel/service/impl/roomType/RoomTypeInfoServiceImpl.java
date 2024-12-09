package com.example.hotel.service.impl.roomType;

import com.example.hotel.dto.common.AmenetiesDTO;
import com.example.hotel.dto.request.AddRoomTypeRequestDTO;
import com.example.hotel.dto.request.ModifyRoomTypeInfoRequestDTO;
import com.example.hotel.dto.request.QueryRoomTypePriceRequestDTO;
import com.example.hotel.dto.response.HotelDetailResponse;
import com.example.hotel.dto.response.RoomAndTypeWithPriceResponse;
import com.example.hotel.entity.RoomTypeInfo;
import com.example.hotel.entity.RoomTypeInfoExample;
import com.example.hotel.mapper.RoomTypeInfoMapper;
import com.example.hotel.service.roomType.RoomTypeInfoService;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.hotel.util.JSONUtil;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class RoomTypeInfoServiceImpl implements RoomTypeInfoService {

  private final RoomTypeInfoMapper roomTypeInfoMapper;



  @Override
  public Map<Long, Map<String, BigDecimal>> getDefaultPrices(List<Long> roomTypeIds) {
    RoomTypeInfoExample example = new RoomTypeInfoExample();
    RoomTypeInfoExample.Criteria criteria = example.createCriteria();
    criteria.andIdIn(roomTypeIds);
    List<RoomTypeInfo> roomTypeList = roomTypeInfoMapper.selectByExample(example);

    Map<Long, Map<String, BigDecimal>> defaultPriceMap = new HashMap<>();
    for (RoomTypeInfo item : roomTypeList) {
      Map<String, BigDecimal> defaultPrices = new HashMap<>();
      defaultPrices.put("weekday", item.getWeekdayPrice());
      defaultPrices.put("weekend", item.getWeekendPrice());
      defaultPriceMap.put(item.getId(), defaultPrices);
    }
    return defaultPriceMap;
  }

  @Override
  public RoomTypeInfo getRoomTypeById(Long id) {

    return roomTypeInfoMapper.selectByPrimaryKey(id);
  }
  @Override
  @SneakyThrows
  public String addRoomType(AddRoomTypeRequestDTO addRoomTypeRequestDTO){
    RoomTypeInfo info = new RoomTypeInfo();
    BeanUtils.copyProperties(addRoomTypeRequestDTO,info);
    if(addRoomTypeRequestDTO.getAmeneties() != null) {
      info.setAmeneties(JSONUtil.getInstance().writeValueAsString(addRoomTypeRequestDTO.getAmeneties()));
    }
    log.info("Room type info is: {}", info.toString());
    try {
      // Code to insert in to database. Map user input to database object
      roomTypeInfoMapper.insertSelective(info);
      log.info("I have executed the query");
      return "Added RoomType to DB Successfully";
    }
    catch (Exception ex){
      return "Failed to add to db: " + ex.getMessage();
    }
  }

  @Override
  @SneakyThrows
  public HotelDetailResponse getRoomOnId(Long id){
    RoomTypeInfo roomType = roomTypeInfoMapper.selectByPrimaryKey(id);
    HotelDetailResponse detailResp = new HotelDetailResponse();
    if(null != roomType){
      detailResp.setRoomCount(roomType.getRoomCount());
      detailResp.setType(roomType.getType());
      detailResp.setHotelId(roomType.getHotelId());
      detailResp.setWeekdayPrice(roomType.getWeekdayPrice());
      detailResp.setWeekendPrice(roomType.getWeekendPrice());
      detailResp.setMaxQuantity(roomType.getMaxQuantity());
      detailResp.setTypeName(roomType.getTypeName());
      if(roomType.getAmeneties() != null) {
        detailResp.setAmeneties(JSONUtil.getInstance().readValue(roomType.getAmeneties(), AmenetiesDTO.class));
      }
      return detailResp;
    }
    else{
      return new HotelDetailResponse();
    }
  }

  @Override
  public String updateRoomType(ModifyRoomTypeInfoRequestDTO requestDto){
    try {
      RoomTypeInfo info = new RoomTypeInfo();
      BeanUtils.copyProperties(requestDto, info);
      if(requestDto.getAmeneties() != null) {
        info.setAmeneties(JSONUtil.getInstance().writeValueAsString(requestDto.getAmeneties()));
      }
      roomTypeInfoMapper.updateByPrimaryKeySelective(info);
      return "Updated roomtype info successfully";
    }
    catch (Exception ex){
      return "Failed to add to db: " + ex.getMessage();
    }
  }
}
