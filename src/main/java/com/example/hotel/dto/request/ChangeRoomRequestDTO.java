package com.example.hotel.dto.request;

import com.example.hotel.dto.request.Base.Request;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@ApiModel("change room request parameter")

public class ChangeRoomRequestDTO extends PayBillRequestDTO {

  @NotEmpty(message = "hotel id cannot be empty")
  @ApiModelProperty(value = "hotelId", required = true, example = "123456")
  @JsonProperty(value = "hotelId")
  private Long hotelId;

  @NotEmpty(message = "order id cannot be empty")
  @ApiModelProperty(value = "orderId", required = true, example = "123456")
  @JsonProperty(value = "orderId")
  private Long orderId;

  @NotEmpty(message = "order detail id cannot be empty")
  @ApiModelProperty(value = "orderDetailId", required = true, example = "123456")
  @JsonProperty(value = "orderDetailId")
  private Long orderDetailId;

  @NotEmpty(message = "room type id cannot be empty")
  @ApiModelProperty(value = "roomTypeId", required = true, example = "123456")
  @JsonProperty(value = "roomTypeId")
  private Long roomTypeId;

  @NotEmpty(message = "realDiff price cannot be empty")
  @ApiModelProperty(value = "realDiffPrice", required = true, example = "123.12")
  @JsonProperty(value = "realDiffPrice")
  private BigDecimal realDiffPrice;

  @NotEmpty(message = "total difference price cannot be empty")
  @ApiModelProperty(value = "totalDiffPrice", required = true, example = "245.98")
  @JsonProperty(value = "totalDiffPrice")
  private BigDecimal totalDiffPrice;

}
