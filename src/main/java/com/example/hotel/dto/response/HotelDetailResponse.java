package com.example.hotel.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.pool2.BaseObject;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@ApiModel("query hotel info result")
public class HotelDetailResponse extends BaseObject {
    @ApiModelProperty(value = "Hotel ID", example = "112", notes = "Unique identifier for the hotel")
    @JsonProperty("hotelId")
    private Long hotelId;

    @ApiModelProperty(value = "Hotel Name", example = "Grand Plaza", notes = "Name of the hotel")
    @JsonProperty("name")
    private String name;

    @ApiModelProperty(value = "City", example = "Limerick", notes = "City where the hotel is located")
    @JsonProperty("city")
    private String city;

    @ApiModelProperty(value = "Address", example = "123 Main Street", notes = "Complete address of the hotel")
    @JsonProperty("address")
    private String address;

    public HotelDetailResponse(Long hotelId, String name, String address, String city) {
        this.hotelId = hotelId;
        this.name = name;
        this.address = address;
        this.city = city;
    }

    public HotelDetailResponse() {

    }
}
