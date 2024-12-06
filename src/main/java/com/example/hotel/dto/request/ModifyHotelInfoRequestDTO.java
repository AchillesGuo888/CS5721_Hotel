package com.example.hotel.dto.request;

import com.example.hotel.dto.request.Base.Request;
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
    private Long hotelId;

    @ApiModelProperty(value = "Hotel Name")
    private String hotelName;

    @ApiModelProperty(value = "City")
    private String city;

    @ApiModelProperty(value = "Address")
    private String address;

    @ApiModelProperty(value = "Phone")
    private String phone;

    @ApiModelProperty(value = "Level")
    private Integer level;
}
