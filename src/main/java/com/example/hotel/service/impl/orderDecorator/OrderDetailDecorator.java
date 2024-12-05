package com.example.hotel.service.impl.orderDecorator;

import com.example.hotel.dto.response.ChangeRoomInfoResponse;
import com.example.hotel.dto.response.OrderDetailInfoResponse;
import com.example.hotel.dto.response.OrderInfoResponse;
import com.example.hotel.entity.OrderDetail;
import com.example.hotel.entity.RoomTypeInfo;
import com.example.hotel.enums.ChangeRoomTypeEnum;
import com.example.hotel.enums.OrderDetailStatusEnum;
import com.example.hotel.service.order.OrderAndDetailService;
import com.example.hotel.service.order.OrderQueryService;
import com.example.hotel.service.roomType.RoomTypeInfoService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;


public class OrderDetailDecorator extends OrderServiceDecorator {

  @Autowired
  private final OrderAndDetailService orderAndDetailService;
  @Autowired
  private final RoomTypeInfoService roomTypeInfoService;

  public OrderDetailDecorator(OrderQueryService orderQueryService,
      OrderAndDetailService orderAndDetailService,
      RoomTypeInfoService roomTypeInfoService) {
    super(orderQueryService);
    this.orderAndDetailService = orderAndDetailService;
    this.roomTypeInfoService = roomTypeInfoService;
  }

  @Override
  protected OrderInfoResponse enhanceOrder(OrderInfoResponse order) {
    OrderInfoResponse response = super.enhanceOrder(order);
    List<OrderDetail> orderDetailList = orderAndDetailService.getOrderDetailByOrderId(
        Arrays.asList(order.getOrderId()));
    List<String> guestNames = new ArrayList<>();
    List<ChangeRoomInfoResponse> changeList = new ArrayList<>();
    List<OrderDetailInfoResponse> orderDetails = new ArrayList<>();
    for (OrderDetail item : orderDetailList) {
      if (!guestNames.contains(item.getGuestName())) {
        guestNames.add(item.getGuestName());
        if (item.getChangeType().equals(ChangeRoomTypeEnum.UNCHANGED.getCode())) {
          RoomTypeInfo roomTypeInfo = roomTypeInfoService.getRoomTypeById(item.getRoomTypeId());
          ChangeRoomInfoResponse changeRoom = ChangeRoomInfoResponse
              .builder().changeDate(item.getCreateTime().toLocalDate())
              .changeTypeDesc(ChangeRoomTypeEnum.getDescByCode(item.getChangeType()))
              .differencePrice(item.getPriceDifference())
              .guestName(item.getGuestName())
              .roomTypeName(roomTypeInfo.getTypeName())
              .build();
          changeList.add(changeRoom);
        }
        if (item.getStatus().equals(OrderDetailStatusEnum.BOOKED.getCode())) {
          OrderDetailInfoResponse detail = OrderDetailInfoResponse.builder()
              .roomNumber(item.getRoomNumber())
              .build();
          orderDetails.add(detail);
        }
      }
    }
    response.getDetailList().addAll(orderDetails);
    response.getChangeRoomList().addAll(changeList);
    return response;
  }


}
