package com.example.hotel.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.pool2.BaseObject;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@ApiModel("room, type and price result")
public class RoomAndTypeWithPriceResponse extends BaseObject {

  @ApiModelProperty("room type id")
  private Long roomTypeId;

  @ApiModelProperty("room type name")
  private String roomTypeName;

  @ApiModelProperty("room type status(0:available,1: full)")
  private Byte status;

  @ApiModelProperty("the count of available room")
  private Integer availableCount;

  @ApiModelProperty("the price of the room type")
  private BigDecimal price;
}
