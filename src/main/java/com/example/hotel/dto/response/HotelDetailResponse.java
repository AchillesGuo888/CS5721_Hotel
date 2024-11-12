package com.example.hotel.dto.response;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.pool2.BaseObject;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@ApiModel("query hotel info result")
public class HotelDetailResponse extends BaseObject {
    private String name;
    private String address;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private String facilities;
    private Integer totalRooms;
    private String phoneNumber;

    public HotelDetailResponse(String name, String address, String city, String state, String country,
                               String zipCode, String facilities, Integer totalRooms, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipCode = zipCode;
        this.facilities = facilities;
        this.totalRooms = totalRooms;
        this.phoneNumber = phoneNumber;
    }

    public HotelDetailResponse() {

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

    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }

    public Integer getTotalRooms() {
        return totalRooms;
    }

    public void setTotalRooms(Integer totalRooms) {
        this.totalRooms = totalRooms;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
