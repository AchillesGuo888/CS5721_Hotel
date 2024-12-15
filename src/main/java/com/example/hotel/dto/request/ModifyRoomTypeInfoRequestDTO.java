package com.example.hotel.dto.request;

import com.example.hotel.dto.common.AmenetiesDTO;
import com.example.hotel.dto.request.Base.Request;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@ApiModel(value = "modify room type info parameter")
public class ModifyRoomTypeInfoRequestDTO extends Request {
    @JsonProperty(value="id")
    private Long id;        // Corresponds to 'hotel_id' column
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
    @JsonProperty(value = "ameneties")
    private AmenetiesDTO ameneties;
}
