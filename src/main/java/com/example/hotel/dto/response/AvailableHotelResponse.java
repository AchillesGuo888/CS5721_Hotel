package com.example.hotel.dto.response;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.pool2.BaseObject;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@ApiModel("query hotel list result")
public class AvailableHotelResponse extends BaseObject {
    public AvailableHotelResponse() {
        String name;
        String address;
        String city;
        String state;
        String country;
        String zipCode;
        Integer totalRooms;
        String facilities;
        String phoneNumber;
    }
}
