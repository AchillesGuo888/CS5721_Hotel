package com.example.hotel.entity;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class OrderDetail {

    private Long id; // Primary Key
    private Long orderId; // Associated Order ID
    private Long userId;
    private Integer roomNumber;
    private BigDecimal priceDifference;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
    private Boolean isDeleted;
    private String remark;
    private Integer roomTypeId; // Current room type ID
    private Integer originalRoomTypeId; // Original room type ID
    private Integer status; // Room Status
    private BigDecimal price;
    private Integer changeType;
    private LocalDateTime changeDate;
    private String guestName;

}