package com.example.hotel.dto.request;

import com.example.hotel.dto.request.Base.Request;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@ApiModel("add room request parameter")
public class AddRoomRequestDTO extends Request {
    @ApiModelProperty(value = "hotel id",required = true, example = "01")
    private int hotelId;
    @ApiModelProperty(value = "room type id",required = true, example = "01")
    private int roomTypeId;
    @ApiModelProperty(value = "update time",required = true, example = "01/01/2025")
    private LocalDateTime updateTime;
    @ApiModelProperty(value = "create time",required = true, example = "01/01/2025")
    private LocalDateTime createTime;
    @ApiModelProperty(value = "is deleted",required = true, example = "0")
    private int isDeleted;
    @ApiModelProperty(value = "room number",required = true, example = "42")
    private String roomNumber;
    @ApiModelProperty(value = "room key",required = true, example = "51544")
    private int roomKey;
    
}




