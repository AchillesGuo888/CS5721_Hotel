package com.example.hotel.dto.request;

import com.example.hotel.dto.request.Base.Request;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@ApiModel(value = "query price and available room count of each room type result")
public class QueryRoomTypePriceRequestDTO extends Request {

  @NotEmpty(message = "hotel id cannot be empty")
  @ApiModelProperty(value = "hotelId", required = true, example = "98776")
  @JsonProperty(value = "hotelId")
  private Long hotelId;

  @NotEmpty(message = "check in date cannot be empty")
  @ApiModelProperty(value = "check in date", required = true, example = "2024-12-24")
  @JsonProperty(value = "startDate")
  private LocalDate startDate;

  @NotEmpty(message = "check out date cannot be empty")
  @ApiModelProperty(value = "check out date", required = true, example = "2024-12-25")
  @JsonProperty(value = "endDate")
  private LocalDate endDate;

  @ApiModelProperty(value = "Maximum number of people per room ", example = "3")
  @JsonProperty(value = "quantity")
  private Integer quantity;
}
