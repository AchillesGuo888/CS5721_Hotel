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
@ApiModel(value = "modify room info parameter")
public class ModifyRoomInfoRequestDTO extends Request {
    @ApiModelProperty(value = "room id",required = true, example = "01")
    @JsonProperty(value = "roomId")
    private Long roomId;
    @ApiModelProperty(value = "room type id",required = true, example = "01")
    @JsonProperty(value = "roomTypeId")
    private Long roomTypeId;
    @ApiModelProperty(value = "is deleted",required = true, example = "0")
    @JsonProperty(value = "isDeleted")
    private Byte isDeleted;
    @ApiModelProperty(value = "room number",required = true, example = "42")
    @JsonProperty(value = "roomNumber")
    private String roomNumber;
    @ApiModelProperty(value = "room key",required = true, example = "51544")
    @JsonProperty(value = "roomKey")
    private String roomKey;
}
