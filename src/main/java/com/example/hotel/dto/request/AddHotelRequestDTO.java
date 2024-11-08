package com.example.hotel.dto.request;

import com.example.hotel.dto.request.Base.Request;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@ApiModel(value = "add hotel info parameter")
public class AddHotelRequestDTO extends Request {
    private String name;         // hotel name
    private String address;      // hotel address
    private String city;         // hotel city
    private String state;        // hotel state
    private String country;      // hotel country
    private String zipCode;      // hotel zipcode
    private String facilities;   // hotel facilities
    private Integer totalRooms;  // number of hotel totalRooms
    private String phoneNumber;  // hotel contact phone number

    // Getter methods for each field
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getFacilities() {
        return facilities;
    }

    public Integer getTotalRooms() {
        return totalRooms;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
