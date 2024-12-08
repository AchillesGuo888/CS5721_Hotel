package com.example.hotel.dto.request;

import com.example.hotel.dto.common.AmenetiesDTO;
import com.example.hotel.dto.request.Base.Request;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.models.auth.In;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@Data
@ToString

public class AddRoomTypeRequestDTO extends Request {

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
