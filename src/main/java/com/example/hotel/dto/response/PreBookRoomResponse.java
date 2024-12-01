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
@ApiModel("pre-book room result")
public class PreBookRoomResponse extends BaseObject {

  private static final long serialVersionUID = -6808963525093889898L;

  @ApiModelProperty("startDate")
  private LocalDate startDate;

  @ApiModelProperty("endDate")
  private LocalDate endDate;

  @ApiModelProperty("phone")
  private String phone;

  @ApiModelProperty("userName")
  private String userName;

  @ApiModelProperty("hotelId")
  private Long hotelId;

  @ApiModelProperty("hotelName")
  private String hotelName;

  @ApiModelProperty("roomTypeId")
  private Long roomTypeId;

  @ApiModelProperty("roomTypeName")
  private String roomTypeName;

  @ApiModelProperty("earnPointsCount")
  private Integer earnPointsCount;

  @ApiModelProperty("membershipDiscount")
  private BigDecimal membershipDiscount;

  @ApiModelProperty("realPrice")
  private BigDecimal realPrice;

  @ApiModelProperty("roomTypePrice")
  private BigDecimal roomTypePrice;

  @ApiModelProperty("totalPrice")
  private BigDecimal totalPrice;

}
