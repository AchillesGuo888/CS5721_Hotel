package com.example.hotel.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.pool2.BaseObject;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@ApiModel("query order info list result")
public class OrderInfoListResponse extends BaseObject {

  @ApiModelProperty("orderId")
  private Long orderId;

  @ApiModelProperty("hotel id")
  private Long hotelId;

  @ApiModelProperty("hotel name")
  private String hotelName;

  @ApiModelProperty("startDate")
  private LocalDate startDate;

  @ApiModelProperty("endDate")
  private LocalDate endDate;
//
//  @ApiModelProperty("room count")
//  private Integer roomCount;
@ApiModelProperty("real price of rooms")
private BigDecimal realPrice;
  @ApiModelProperty("room type name")
  private String roomTypeName;



}
