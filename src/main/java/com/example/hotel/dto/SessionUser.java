package com.example.hotel.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.pool2.BaseObject;

@EqualsAndHashCode(callSuper = true)
@Data
public class SessionUser extends BaseObject {

  String userName;
  Byte userType;
}
