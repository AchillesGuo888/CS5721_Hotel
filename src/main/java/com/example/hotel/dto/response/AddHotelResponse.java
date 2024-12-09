package com.example.hotel.dto.response;

import lombok.Data;

/**
 * Add hotel response results
 */
@Data
public class AddHotelResponse {
    private Long hotelId;
    private String hotelName;
    private String message;
}
