package com.example.hotel.dto.request;

import com.example.hotel.dto.request.Base.Request;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@ApiModel(value = "modify hotel info parameter")
public class ModifyHotelInfoRequestDTO extends Request {
    @NotNull(message = "Hotel ID cannot be null")
    @ApiModelProperty(value = "Hotel ID", required = true)
    @JsonProperty(value = "hotelId")
    private Long hotelId;

    @ApiModelProperty(value = "Hotel Name")
    @JsonProperty(value = "hotelName")
    private String hotelName;

    @ApiModelProperty(value = "City")
    @JsonProperty(value = "city")
    private String city;

    @ApiModelProperty(value = "Address")
    @JsonProperty(value = "address")
    private String address;

    @ApiModelProperty(value = "Phone")
    @JsonProperty(value = "phone")
    private String phone;

    @ApiModelProperty(value = "Level")
    @JsonProperty(value = "level")
    private Integer level;
}
