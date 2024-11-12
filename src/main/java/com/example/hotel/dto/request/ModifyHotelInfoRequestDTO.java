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
@ApiModel(value = "modify hotel info parameter")
public class ModifyHotelInfoRequestDTO extends Request {
    @ApiModelProperty(value = "Hotel ID", required = true)
    private Long hotelId;

    @ApiModelProperty(value = "Hotel name", required = true)
    private String name;

    @ApiModelProperty(value = "Hotel address", required = true)
    private String address;

    @ApiModelProperty(value = "Hotel city", required = true)
    private String city;

    @ApiModelProperty(value = "Hotel state", required = true)
    private String state;

    @ApiModelProperty(value = "Hotel country", required = true)
    private String country;

    @ApiModelProperty(value = "Hotel zip code", required = true)
    private String zipCode;

    @ApiModelProperty(value = "Hotel facilities", required = true)
    private String facilities;

    @ApiModelProperty(value = "Hotel total rooms", required = true)
    private Integer totalRooms;

    @ApiModelProperty(value = "Hotel phone number", required = true)
    private String phoneNumber;
    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId){
        this.hotelId = hotelId;
    }
}
