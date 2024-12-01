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
@ApiModel("change order room count result")
public class ChangeOrderRoomCountResponse extends BaseObject {

  private static final long serialVersionUID = -6808963525093889898L;


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
