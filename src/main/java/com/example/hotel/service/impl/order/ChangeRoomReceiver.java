package com.example.hotel.service.impl.order;

import org.springframework.stereotype.Component;

@Component
public class ChangeRoomReceiver {

  public Integer addRoom(Integer roomCount) {
    return roomCount + 1;
  }

  public Integer removeRoom(Integer roomCount) {
    if (roomCount <= 1) {
      return 1;
    } else {
      return roomCount - 1;
    }

  }
}
