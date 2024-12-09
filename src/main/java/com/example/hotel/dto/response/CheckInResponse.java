package com.example.hotel.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

import org.apache.commons.pool2.BaseObject;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@ApiModel("check in result")
public class CheckInResponse extends BaseObject {
  @ApiModelProperty("room key")
  private String roomKey;
  @ApiModelProperty("room number")
  private String roomNumber;
}
