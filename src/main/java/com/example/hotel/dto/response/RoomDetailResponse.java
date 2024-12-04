package com.example.hotel.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

import org.apache.commons.pool2.BaseObject;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@ApiModel("query room info result")
public class RoomDetailResponse extends BaseObject {
    @ApiModelProperty("room id")
    private Long roomId;
    @ApiModelProperty("room key")
    private String roomKey;
    @ApiModelProperty("room number")
    private String roomNumber;
    @ApiModelProperty("hotel id")
    private Long hotelId;
    @ApiModelProperty("room type id")
    private Long roomTypeId;
    @ApiModelProperty("update time")
    private LocalDateTime updateTime;
    @ApiModelProperty("create time")
    private LocalDateTime createTime;
    @ApiModelProperty("price range min")
    private Long priceRangeMin;
    @ApiModelProperty("price range max")
    private Long priceRangeMax;
}