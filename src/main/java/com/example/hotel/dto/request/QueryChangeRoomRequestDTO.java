package com.example.hotel.dto.request;

import com.example.hotel.dto.request.Base.Request;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@ApiModel("change room request parameter")

public class QueryChangeRoomRequestDTO extends Request {

  @NotEmpty(message = "hotel id cannot be empty")
  @ApiModelProperty(value = "hotelId", required = true, example = "123456")
  @JsonProperty(value = "hotelId")
  private Long hotelId;

  @NotEmpty(message = "order id cannot be empty")
  @ApiModelProperty(value = "orderId", required = true, example = "123456")
  @JsonProperty(value = "orderId")
  private Long orderId;

  @NotEmpty(message = "order detaili id cannot be empty")
  @ApiModelProperty(value = "orderDetailId", required = true, example = "123456")
  @JsonProperty(value = "orderDetailId")
  private Long orderDetailId;

  @NotEmpty(message = "roomTypeId id cannot be empty")
  @ApiModelProperty(value = "roomTypeId", required = true, example = "123456")
  @JsonProperty(value = "roomTypeId")
  private Long roomTypeId;

}
