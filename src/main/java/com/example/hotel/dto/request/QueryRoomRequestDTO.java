package com.example.hotel.dto.request;

import com.example.hotel.dto.request.Base.Request;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@ApiModel(value = "query room info parameter")
public class QueryRoomRequestDTO extends Request {
    @ApiModelProperty(value = "room id",required = true, example = "01")
    private Long roomId;
}
