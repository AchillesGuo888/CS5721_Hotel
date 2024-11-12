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
@ApiModel(value = "query hotel info parameter")
public class QueryHotelRequestDTO extends Request {
    @ApiModelProperty(value = "Hotel ID")
    private Long hotelId;

    @ApiModelProperty(value = "Hotel name")
    private String name;

    @ApiModelProperty(value = "Hotel address")
    private String address;

    @ApiModelProperty(value = "City of the hotel")
    private String city;

    @ApiModelProperty(value = "State of the hotel")
    private String state;

    @ApiModelProperty(value = "Country of the hotel")
    private String country;

    @ApiModelProperty(value = "Zip code of the hotel")
    private String zipCode;

    @ApiModelProperty(value = "Page number for pagination")
    private int page = 1;

    @ApiModelProperty(value = "Number of items per page")
    private int size = 10;

    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
