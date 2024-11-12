package com.example.hotel.dto.request;

import com.example.hotel.dto.request.Base.Request;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@ApiModel("book room request parameter")
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

  @NotEmpty(message = "contact name cannot be empty")
  @ApiModelProperty(value = "contactName", required = true, example = "2024-12-25")
  private String contactName;

  @NotEmpty(message = "contact phone cannot be empty")
  @ApiModelProperty(value = "contactPhone", required = true, example = "2024-12-25")
  private String contactPhone;

  @NotEmpty(message = "guest names cannot be empty")
  @ApiModelProperty(value = "guestNames", required = true, example = "[a,b]")
  private List<String> guestNames;

  @NotEmpty(message = "Estimated time of arrival cannot be empty")
  @ApiModelProperty(value = "estimatedArrivalTime", required = true, example = "17:00")
  private LocalTime estimatedArrivalTime;

  @ApiModelProperty(value = "room count", example = "1")
  private Integer roomCount;



}
