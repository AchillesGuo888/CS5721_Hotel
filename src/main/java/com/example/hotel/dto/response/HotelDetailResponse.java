package com.example.hotel.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.*;
import org.apache.commons.pool2.BaseObject;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("query hotel info result")
public class HotelDetailResponse extends BaseObject {
    @JsonProperty(value="hotelId")
    private Long hotelId;        // Corresponds to 'hotel_id' column
    @JsonProperty(value="typeName")
    private String typeName;     // Corresponds to 'type_name' column
    @JsonProperty(value="type")
    private Byte type;        // Corresponds to 'type' column
    @JsonProperty(value="weekdayPrice")
    private BigDecimal weekdayPrice; // Corresponds to 'weekday_price' column
    @JsonProperty(value="weekendPrice")
    private BigDecimal weekendPrice; // Corresponds to 'weekend_price' column
    @JsonProperty(value="roomCount")
    private Integer roomCount;   // Corresponds to 'room_count' column
    @JsonProperty(value="maxQuantity")
    private Integer maxQuantity;
}
