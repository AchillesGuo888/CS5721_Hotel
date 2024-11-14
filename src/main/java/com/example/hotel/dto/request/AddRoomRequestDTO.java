package com.example.hotel.dto.request;

import com.example.hotel.dto.request.Base.Request;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

import org.springframework.cglib.core.Local;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@ApiModel("add room request parameter")
public class AddRoomRequestDTO extends Request {
    @ApiModelProperty(value = "hotel id",required = true, example = "01")
    @JsonProperty(value = "hotelId")
    private Long hotelId;
    @ApiModelProperty(value = "room type id",required = true, example = "01")
    @JsonProperty(value = "roomTypeId")
    private Long roomTypeId;
    /*to be received from db: */
    @ApiModelProperty(value = "update time",required = true, example = "01/01/2025")
    @JsonProperty(value = "updateTime")
    private LocalDateTime updateTime;
    /*to be received from db: */
    @ApiModelProperty(value = "create time",required = true, example = "01/01/2025")
    @JsonProperty(value = "createTime")
    private LocalDateTime createTime;
    /*this value will ALWAYS be 0 */
    @ApiModelProperty(value = "is deleted",required = true, example = "0")
    @JsonProperty(value = "isDeleted")
    private Byte isDeleted = 0;
    @ApiModelProperty(value = "room number",required = true, example = "42")
    @JsonProperty(value = "roomNumber")
    private String roomNumber;
    /*not sure */
    @ApiModelProperty(value = "room key",required = true, example = "51544")
    @JsonProperty(value = "roomKey")
    private String roomKey;    
}




