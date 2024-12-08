package com.example.hotel.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.pool2.BaseObject;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@ApiModel("query order detail info result")
public class OrderDetailInfoResponse extends BaseObject {

  @ApiModelProperty("room number")
  private String roomNumber;

  @ApiModelProperty("room key")
  private String roomKey;

}
