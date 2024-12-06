package com.example.hotel.dto.response;

import lombok.Data;

/**
 * 添加酒店响应结果
 */
@Data
public class AddHotelResponse {
    private Long hotelId;
    private String hotelName;
    private String message;
}
