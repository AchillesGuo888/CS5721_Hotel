package com.example.hotel.config;

import com.example.hotel.mapper.HotelInfoMapper;
import com.example.hotel.mapper.OrderBaseMapper;
import com.example.hotel.mapper.RoomInfoMapper;


import com.example.hotel.service.impl.orderDecorator.BasicOrderService;
import com.example.hotel.service.impl.orderDecorator.HotelDetailDecorator;
import com.example.hotel.service.impl.orderDecorator.OrderDetailDecorator;
import com.example.hotel.service.impl.orderDecorator.RoomDetailDecorator;
import com.example.hotel.service.order.OrderAndDetailService;
import com.example.hotel.service.order.OrderQueryService;
import com.example.hotel.service.roomType.RoomTypeInfoService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class OrderQueryConfig {

  private final RoomInfoMapper roomInfoMapper;

  private final OrderBaseMapper orderBaseMapper;

  private final OrderAndDetailService orderAndDetailService;

  private final RoomTypeInfoService roomTypeInfoService;

  private final HotelInfoMapper hotelInfoMapper;

  @Bean
  public OrderQueryService orderQueryService() {
    //get order base info
    OrderQueryService basicService = new BasicOrderService(orderBaseMapper);
    //get order detail info
    OrderQueryService orderDetailDecorator = new OrderDetailDecorator(basicService,
        orderAndDetailService, roomTypeInfoService);
    //get room type info
    OrderQueryService roomDetailDecorator = new RoomDetailDecorator(orderDetailDecorator,
        roomInfoMapper);
    //get hotel info
    return new HotelDetailDecorator(roomDetailDecorator, hotelInfoMapper);
  }

}
