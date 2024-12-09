package com.example.hotel.dto.request;

import com.example.hotel.dto.request.Base.Request;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@ApiModel(value = "add hotel info parameter")
public class AddHotelRequestDTO extends Request {
    @JsonProperty(value = "hotelName")
    private String hotelName;
    @JsonProperty(value = "city")
    private String city;
    @JsonProperty(value = "address")
    private String address;
    @JsonProperty(value = "phoneNumber")
    private String phoneNumber;
    @JsonProperty(value = "level")
    private Integer level;       // Hotel star rating (e.g. 5 )
}
