package com.example.hotel.service.impl.orderDecorator;

import com.example.hotel.dto.response.OrderInfoResponse;
import com.example.hotel.entity.HotelInfo;
import com.example.hotel.mapper.HotelInfoMapper;
import com.example.hotel.service.order.OrderQueryService;
import org.springframework.beans.factory.annotation.Autowired;

public class HotelDetailDecorator extends OrderServiceDecorator {

  @Autowired
  private final HotelInfoMapper hotelInfoMapper;

  public HotelDetailDecorator(OrderQueryService orderQueryService,
      HotelInfoMapper hotelInfoMapper) {
    super(orderQueryService);

    this.hotelInfoMapper = hotelInfoMapper;
  }

  @Override
  protected OrderInfoResponse enhanceOrder(OrderInfoResponse order) {

    HotelInfo hotelInfo = hotelInfoMapper.selectByPrimaryKey(order.getHotelId());
    order.setHotelName(hotelInfo.getHotelName());
    order.setCity(hotelInfo.getCity());
    return order;
  }


}
