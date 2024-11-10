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
@ApiModel(value = "modify room info parameter")
public class ModifyRoomInfoRequestDTO extends Request {
    @ApiModelProperty(value = "room id",required = true, example = "01")
    private Long roomId;
    @ApiModelProperty(value = "hotel id",required = true, example = "01")
    private Long hotelId;
    @ApiModelProperty(value = "room type id",required = true, example = "01")
    private Long roomTypeId;
    @ApiModelProperty(value = "update time",required = true, example = "01/01/2025")
    private LocalDateTime updateTime;
    @ApiModelProperty(value = "create time",required = true, example = "01/01/2025")
    private LocalDateTime createTime;
    @ApiModelProperty(value = "is deleted",required = true, example = "0")
    private Byte isDeleted;
    @ApiModelProperty(value = "room number",required = true, example = "42")
    private String roomNumber;
    @ApiModelProperty(value = "room key",required = true, example = "51544")
    private String roomKey;
}
