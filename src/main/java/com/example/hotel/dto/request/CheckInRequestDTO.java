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
@ApiModel("check in request parameter")
public class CheckInRequestDTO extends Request {
    @ApiModelProperty(value = "room id",required = true, example = "01")
    private Long roomId;
    @ApiModelProperty(value = "update time",required = true, example = "01/01/2025")
    private LocalDateTime updateTime;
    @ApiModelProperty(value = "room number",required = true, example = "42")
    private String roomNumber;
}
