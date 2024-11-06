package com.example.hotel.dto.request;

import com.example.hotel.dto.request.Base.Request;
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
@ApiModel(value = "query hotel info parameter")
public class QueryHotelRequestDTO extends Request {

  @NotEmpty(message = "city cannot be empty")
  @ApiModelProperty(value = "city", required = true, example = "Limerick")
  private String city;

  @ApiModelProperty(value = "address", example = "University")
  private String address;

  @ApiModelProperty(value = "name", example = "University")
  private String name;

  @NotEmpty(message = "check in date cannot be empty")
  @ApiModelProperty(value = "check in date", required = true, example = "2024-12-24")
  private LocalDate startDate;

  @NotEmpty(message = "check out date cannot be empty")
  @ApiModelProperty(value = "check out date", required = true, example = "2024-12-25")
  private LocalDate endDate;

  @ApiModelProperty(value = "Maximum number of people per room ", example = "3")
  private Integer quantity;

}
