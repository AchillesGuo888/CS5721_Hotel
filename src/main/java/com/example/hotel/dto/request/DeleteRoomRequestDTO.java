package com.example.hotel.dto.request;

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
@ApiModel(value = "delete room info parameter")
public class DeleteRoomRequestDTO extends Request {
    @ApiModelProperty(value = "room id",required = true, example = "01")
    @JsonProperty(value = "roomId")
    private Long roomId;
}
