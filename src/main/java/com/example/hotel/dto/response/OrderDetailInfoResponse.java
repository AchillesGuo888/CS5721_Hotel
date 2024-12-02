package com.example.hotel.dto.response;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.pool2.BaseObject;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@ApiModel("query order detail info result")
public class OrderDetailInfoResponse extends BaseObject {
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
