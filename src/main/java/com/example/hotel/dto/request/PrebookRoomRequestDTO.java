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
@ApiModel("pre-book room request parameter")
public class PrebookRoomRequestDTO extends Request {

  @NotEmpty(message = "hotel id cannot be empty")
  @ApiModelProperty(value = "hotelId", required = true, example = "123456")
  private Long hotelId;

  @NotEmpty(message = "room type id cannot be empty")
  @ApiModelProperty(value = "roomTypeId", required = true, example = "123456")
  private Long roomTypeId;

  @NotEmpty(message = "check in date cannot be empty")
  @ApiModelProperty(value = "startDate", required = true, example = "2024-12-11")
  private LocalDate startDate;

  @NotEmpty(message = "check out date cannot be empty")
  @ApiModelProperty(value = "endDate", required = true, example = "2024-12-25")
  private LocalDate endDate;

  @ApiModelProperty(value = "room count(default count is 1 room)", example = "1")
  private Integer roomCount = 1;

}
