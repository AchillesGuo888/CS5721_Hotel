package com.example.hotel.dto.request;

import java.time.LocalDateTime;

import com.example.hotel.dto.request.Base.Request;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@ApiModel(value = "query room info list parameter")
public class QueryRoomListRequestDTO extends Request {
    @ApiModelProperty(value = "hotel id",required = false, example = "01")
    @JsonProperty(value = "hotelId")
    private Long hotelId;
    @ApiModelProperty(value = "room type id",required = false, example = "01")
    @JsonProperty(value = "roomTypeId")
    private Long roomTypeId;
    @ApiModelProperty(value = "price range min",required = false, example = "20")
    @JsonProperty(value = "priceRangeMin")
    private Long priceRangeMin;
    @ApiModelProperty(value = "price range max",required = false, example = "70")
    @JsonProperty(value = "priceRangeMax")
    private Long priceRangeMax;
    @ApiModelProperty(value = "page",required = false, example = "1")
    @JsonProperty(value = "page")
    private Long page;
    @ApiModelProperty(value = "page size",required = false, example = "20")
    @JsonProperty(value = "pageSize")
    private Long pageSize;
    @ApiModelProperty(value = "update time",required = false, example = "01/01/2025")
    @JsonProperty(value = "updateTime")
    private LocalDateTime updateTime;
    @ApiModelProperty(value = "create time",required = false, example = "01/01/2025")
    @JsonProperty(value = "createTime")
    private LocalDateTime createTime;
    @ApiModelProperty(value = "is deleted",required = false, example = "0")
    @JsonProperty(value = "isDeleted")
    private Byte isDeleted;
    @ApiModelProperty(value = "room number",required = false, example = "42")
    @JsonProperty(value = "roomNumber")
    private String roomNumber;  
}
