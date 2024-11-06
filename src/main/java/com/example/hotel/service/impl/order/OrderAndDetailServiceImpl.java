package com.example.hotel.service.impl.order;

import com.example.hotel.dto.AvailableRoomCountDTO;
import com.example.hotel.dto.request.QueryHotelRequestDTO;
import com.example.hotel.dto.response.AvailableHotelResponse;
import com.example.hotel.entity.HotelInfo;
import com.example.hotel.entity.HotelInfoExample;
import com.example.hotel.mapper.HotelInfoMapper;
import com.example.hotel.mapper.ext.OrderAndDetailMapperExt;
import com.example.hotel.service.hotel.HotelAndTypeService;
import com.example.hotel.service.order.OrderAndDetailService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class OrderAndDetailServiceImpl implements OrderAndDetailService {

private final OrderAndDetailMapperExt orderAndDetailMapperExt;
  @Override
  public List<AvailableRoomCountDTO> queryAvailableRoomType(List<Long> hotelIds,
      LocalDate startDate, LocalDate endDate,Integer quantity) {
    return orderAndDetailMapperExt.getAvailableRoomType(hotelIds,
        startDate, endDate,quantity);
//    Map<Long, List<AvailableRoomCountDTO>> hotelAvailableRoomMap = new HashMap<>();
//    if (CollectionUtils.isNotEmpty(list)){
//      for (AvailableRoomCountDTO item: list){
//        if (CollectionUtils.isEmpty(hotelAvailableRoomMap.get(item.hotelId))&&item.getAvailableCount()>0){
//          hotelAvailableRoomMap.put(item.getHotelId(),new ArrayList<AvailableRoomCountDTO>());
//        }
//        if (item.getAvailableCount()>0){
//          hotelAvailableRoomMap.get(item.hotelId).add(item);
//        }
//      }
//    }
  }
}
