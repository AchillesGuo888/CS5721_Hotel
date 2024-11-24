package com.example.hotel.dto.request;

import java.time.LocalDateTime;

import com.example.hotel.dto.request.Base.Request;
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
    private Long hotelId;
    @ApiModelProperty(value = "room type id",required = false, example = "01")
    private Long roomTypeId;
    @ApiModelProperty(value = "update time",required = false, example = "01/01/2025")
    private LocalDateTime updateTime;
    @ApiModelProperty(value = "create time",required = false, example = "01/01/2025")
    private LocalDateTime createTime;
    @ApiModelProperty(value = "is deleted",required = false, example = "0")
    private Byte isDeleted;
    @ApiModelProperty(value = "room number",required = false, example = "42")
    private String roomNumber;
}
