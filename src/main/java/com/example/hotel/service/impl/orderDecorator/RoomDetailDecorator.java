package com.example.hotel.service.impl.orderDecorator;

import com.example.hotel.dto.response.OrderDetailInfoResponse;
import com.example.hotel.dto.response.OrderInfoResponse;
import com.example.hotel.entity.RoomInfo;
import com.example.hotel.entity.RoomInfoExample;
import com.example.hotel.enums.OrderStatusEnum;
import com.example.hotel.mapper.RoomInfoMapper;
import com.example.hotel.service.order.OrderQueryService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class RoomDetailDecorator extends OrderServiceDecorator {

  @Autowired
  private final RoomInfoMapper roomInfoMapper;

  public RoomDetailDecorator(OrderQueryService orderDetailDecorator,
      RoomInfoMapper roomInfoMapper) {
    super(orderDetailDecorator);
    this.roomInfoMapper = roomInfoMapper;
  }

  @Override
  protected OrderInfoResponse enhanceOrder(OrderInfoResponse order) {
    OrderInfoResponse response = super.enhanceOrder(order);
    if (CollectionUtils.isNotEmpty(order.getDetailList()) && order.getStatus()
        .equals(OrderStatusEnum.CHECK_IN.getCode())) {
      List<String> roomNumberList = order.getDetailList().stream().map(x -> x.getRoomNumber())
          .collect(
              Collectors.toList());
      RoomInfoExample example = new RoomInfoExample();
      RoomInfoExample.Criteria criteria = example.createCriteria();
      criteria.andIsDeletedEqualTo((byte) 0)
          .andRoomNumberIn(roomNumberList)
          .andHotelIdEqualTo(order.getHotelId());
      List<RoomInfo> roomInfos = roomInfoMapper.selectByExample(example);
      Map<String, String> numberKeyMap = new HashMap<>();
      for (RoomInfo item : roomInfos) {
        numberKeyMap.put(item.getRoomNumber(), item.getRoomKey());
      }
      for (OrderDetailInfoResponse item : response.getDetailList()) {
        item.setRoomKey(numberKeyMap.get(item.getRoomNumber()));
      }
    }
    return response;
  }


}
