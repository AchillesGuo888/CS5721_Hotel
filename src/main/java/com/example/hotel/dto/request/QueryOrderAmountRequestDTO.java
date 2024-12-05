package com.example.hotel.dto.request;

import com.example.hotel.dto.request.Base.Request;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@ApiModel("calculate order amount request parameter")
public class QueryOrderAmountRequestDTO extends Request {

  @NotEmpty(message = "check in date cannot be empty")
  @ApiModelProperty(value = "startDate", required = true, example = "2024-12-11")
  @JsonProperty(value = "startDate")
  private LocalDate startDate;

  @NotEmpty(message = "check out date cannot be empty")
  @ApiModelProperty(value = "endDate", required = true, example = "2024-12-25")
  @JsonProperty(value = "endDate")
  private LocalDate endDate;

  @ApiModelProperty(value = "room count(default count is 1 room)", example = "1")
  @JsonProperty(value = "roomCount")
  private Integer roomCount = 1;

  @ApiModelProperty(value = "room type price", example = "200.00")
  @JsonProperty(value = "roomTypePrice")
  private BigDecimal roomTypePrice;

}
