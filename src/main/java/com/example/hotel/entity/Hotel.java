package com.example.hotel.entity;

import com.example.hotel.common.base.BaseEntity;

public class Hotel extends BaseEntity {
    private String name; // hotel name
    private String address; // hotel address
    private String city; // hotel city
    private String state; // hotel state
    private String country; // hotel country
    private String zipCode; // hotel zipcode
    private String facilities;//hotel facilities
    private Integer totalRooms; // number of hotel totalRooms
    private String phoneNumber; // hotel connect phone number

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }

    public void setTotalRooms(Integer totalRooms) {
        this.totalRooms = totalRooms;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
