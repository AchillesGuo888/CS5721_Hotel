package com.example.hotel.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.pool2.BaseObject;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@ApiModel("change room detail result")
public class ChangeRoomInfoResponse extends BaseObject {

  @ApiModelProperty("roomTypeName")
  private String roomTypeName;

  @ApiModelProperty("changeDate")
  private LocalDate changeDate;

  @ApiModelProperty("differencePrice")
  private BigDecimal differencePrice;

  @ApiModelProperty("guestName")
  private String guestName;

  @ApiModelProperty("changeTypeDesc")
  private String changeTypeDesc;

  @ApiModelProperty("roomNumber")
  private String roomNumber;

}
