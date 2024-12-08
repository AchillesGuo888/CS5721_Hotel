package com.example.hotel.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.pool2.BaseObject;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@ApiModel("query order info result")
public class OrderInfoResponse extends BaseObject {

  @ApiModelProperty("hotelName")
  private String hotelName;

  @ApiModelProperty("roomTypeName")
  private String roomTypeName;

  @ApiModelProperty("startDate")
  private LocalDate startDate;

  @ApiModelProperty("endDate")
  private LocalDate endDate;

  @ApiModelProperty("dates")
  private Integer dates;

  @ApiModelProperty("realPrice")
  private BigDecimal realPrice;

  @ApiModelProperty("orderNumber")
  private String orderNumber;

  @ApiModelProperty("createTime")
  private LocalDateTime createTime;

  @ApiModelProperty("guestNames")
  private List<String> guestNames;

  @ApiModelProperty("earnPointsCount")
  private Integer earnPointsCount;

  @ApiModelProperty("membershipDiscount")
  private BigDecimal membershipDiscount;

  @ApiModelProperty("totalPrice")
  private BigDecimal totalPrice;

  @ApiModelProperty(value = "estimatedArrivalTime")
  private LocalTime estimatedArrivalTime;

  @ApiModelProperty("contactPhone")
  private String contactPhone;

  @ApiModelProperty("contactName")
  private String contactName;

  @ApiModelProperty("changeRoomList")
  private List<ChangeRoomInfoResponse> changeRoomList = new ArrayList<>();

  @ApiModelProperty("id")
  private Long orderId;

  @ApiModelProperty("orderDetailList")
  private List<OrderDetailInfoResponse> detailList = new ArrayList<>();

  @ApiModelProperty("status")
  private Byte status;

  @ApiModelProperty("statusDesc")
  private String statusDesc;

  @ApiModelProperty("hotelId")
  private Long hotelId;

  @ApiModelProperty("address")
  private String address;

  @ApiModelProperty("city")
  private String city;

}
