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
    @ApiModelProperty(value = "room number",required = true, example = "42")
    @JsonProperty(value = "roomNumber")
    private String roomNumber; 
}




