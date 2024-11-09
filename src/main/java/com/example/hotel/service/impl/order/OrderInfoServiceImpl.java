package com.example.hotel.service.impl.order;

import com.example.hotel.dto.AvailableRoomCountDTO;
import com.example.hotel.mapper.ext.OrderAndDetailMapperExt;
import com.example.hotel.service.order.OrderAndDetailService;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class OrderInfoServiceImpl implements OrderAndDetailService {

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
