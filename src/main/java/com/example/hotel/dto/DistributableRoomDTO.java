package com.example.hotel.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.pool2.BaseObject;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder

public class DistributableRoomDTO extends BaseObject{

  public Long roomId;
  public String roomNumber;

}
