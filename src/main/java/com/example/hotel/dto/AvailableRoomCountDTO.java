package com.example.hotel.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.pool2.BaseObject;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder

public class AvailableRoomCountDTO extends BaseObject {

  public Long roomTypeId;
  public Long hotelId;
  public Integer bookedRoomsCount;
  public Integer availableCount;
  public String roomTypeName;
}
