package com.example.hotel.dto.request;

import com.example.hotel.dto.request.Base.Request;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
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
public class BookRoomRequestDTO extends Request {

  @NotEmpty(message = "hotel id cannot be empty")
  @ApiModelProperty(value = "hotelId", required = true, example = "123456")
  @JsonProperty(value = "hotelId")
  private Long hotelId;

  @NotEmpty(message = "room type id cannot be empty")
  @ApiModelProperty(value = "roomTypeId", required = true, example = "123456")
  @JsonProperty(value = "roomTypeId")
  private Long roomTypeId;

  @NotEmpty(message = "check in date cannot be empty")
  @ApiModelProperty(value = "startDate", required = true, example = "2024-12-11")
  @JsonProperty(value = "startDate")
  private LocalDate startDate;

  @NotEmpty(message = "check out date cannot be empty")
  @ApiModelProperty(value = "endDate", required = true, example = "2024-12-25")
  @JsonProperty(value = "endDate")
  private LocalDate endDate;

  @NotEmpty(message = "contact name cannot be empty")
  @ApiModelProperty(value = "contactName", required = true, example = "Shane")
  @JsonProperty(value = "contactName")
  private String contactName;

  @NotEmpty(message = "contact phone cannot be empty")
  @ApiModelProperty(value = "contactPhone", required = true, example = "838901234")
  @JsonProperty(value = "contactPhone")
  private String contactPhone;

  @NotEmpty(message = "guest names cannot be empty")
  @ApiModelProperty(value = "guestNames", required = true, example = "[a,b]")
  @JsonProperty(value = "guestNames")
  private List<String> guestNames;

  @NotEmpty(message = "Estimated time of arrival cannot be empty")
  @ApiModelProperty(value = "estimatedArrivalTime", required = true, example = "17:00")
  @JsonProperty(value = "estimatedArrivalTime")
  private LocalTime estimatedArrivalTime;

  @ApiModelProperty(value = "room count", example = "1")
  @JsonProperty(value = "roomCount")
  private Integer roomCount;

  @NotEmpty(message = "room price cannot be empty")
  @ApiModelProperty(value = "roomPrice", required = true, example = "200.00")
  @JsonProperty(value = "roomPrice")
  private BigDecimal roomPrice;

  @NotEmpty(message = "room real price cannot be empty")
  @ApiModelProperty(value = "roomRealPrice", required = true, example = "180.00")
  @JsonProperty(value = "roomRealPrice")
  private BigDecimal roomRealPrice;

  @NotEmpty(message = "Total price cannot be empty")
  @ApiModelProperty(value = "totalPrice", required = true, example = "2000.00")
  @JsonProperty(value = "totalPrice")
  private BigDecimal totalPrice;

  @NotEmpty(message = "Real price cannot be empty")
  @ApiModelProperty(value = "realPrice", required = true, example = "1800.00")
  @JsonProperty(value = "realPrice")
  private BigDecimal realPrice;

  @NotEmpty(message = "earn points cannot be empty")
  @ApiModelProperty(value = "earnPointsCount", required = true, example = "666")
  @JsonProperty(value = "earnPointsCount")
  private Integer earnPointsCount;

  @NotEmpty(message = "Membership discount cannot be empty")
  @ApiModelProperty(value = "membershipDiscount", required = true, example = "200.00")
  @JsonProperty(value = "membershipDiscount")
  private BigDecimal membershipDiscount;

  @ApiModelProperty(value = "userId", hidden = true, example = "878787")
  @JsonProperty(value = "userId")
  private String userId;

}
