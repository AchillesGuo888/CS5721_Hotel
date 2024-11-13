package com.example.hotel.service.impl.order;

import com.example.hotel.dto.AvailableRoomCountDTO;
import com.example.hotel.dto.DistributableRoomDTO;
import com.example.hotel.dto.request.BookRoomRequestDTO;

import com.example.hotel.entity.OrderDetail;

import com.example.hotel.mapper.OrderDetailMapper;
import com.example.hotel.mapper.ext.OrderAndDetailMapperExt;

import com.example.hotel.service.order.OrderAndDetailService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class OrderAndDetailServiceImpl implements OrderAndDetailService {

  private final OrderAndDetailMapperExt orderAndDetailMapperExt;
  private final OrderDetailMapper orderDetailMapper;

  @Override
  public List<AvailableRoomCountDTO> queryAvailableRoomType(List<Long> hotelIds,
      LocalDate startDate, LocalDate endDate,Integer quantity,List<Long> roomTypeIds) {
    return orderAndDetailMapperExt.getAvailableRoomType(hotelIds,
        startDate, endDate,quantity,roomTypeIds);
  }

  @Override
  public void addOrderDetail(Long orderBaseId, BookRoomRequestDTO requestDTO) {
    List<DistributableRoomDTO> roomList = orderAndDetailMapperExt.getDistributableRoomList(requestDTO.getRoomTypeId(),requestDTO.getHotelId(),requestDTO.getStartDate(),requestDTO.getEndDate());
    for (int i=0;i<roomList.size();i++){
      OrderDetail detail = new OrderDetail();
      BeanUtils.copyProperties(requestDTO,detail);
      detail.setOrderId(orderBaseId);
      detail.setUserId(requestDTO.getUserId());
      detail.setGuestName(requestDTO.getGuestNames().get(i));
      detail.setRoomNumber(roomList.get(i).getRoomNumber());
      detail.setPrice(requestDTO.getRoomRealPrice());
      orderDetailMapper.insertSelective(detail);
    }
  }
}
