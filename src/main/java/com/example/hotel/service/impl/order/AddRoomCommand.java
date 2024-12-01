package com.example.hotel.service.impl.order;

import com.example.hotel.service.command.ChangeRoomCountCommand;
import lombok.extern.slf4j.Slf4j;


@Slf4j

public class AddRoomCommand implements ChangeRoomCountCommand {

  private ChangeRoomReceiver receiver;
  private Integer roomCount;

  public AddRoomCommand(ChangeRoomReceiver receiver, Integer roomCount) {
    this.receiver = receiver;
    this.roomCount = roomCount;
  }

  @Override
  public Integer execute() {

    return receiver.addRoom(roomCount);
  }
}
