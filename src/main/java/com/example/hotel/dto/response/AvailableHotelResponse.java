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
@ApiModel("query hotel list result(with price)")
public class AvailableHotelResponse extends BaseObject {
  @ApiModelProperty("hotel id")
  private Long hotelId;

  @ApiModelProperty("hotel name")
  private String hotelName;

  @ApiModelProperty("hotel status(0:available,1: full)")
  private Byte status;

  @ApiModelProperty("the lowest price")
  private BigDecimal price;

}
