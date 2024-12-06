package com.example.hotel.dto.request;

import com.example.hotel.dto.request.Base.Request;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@ApiModel(value = "add hotel info parameter")
public class AddHotelRequestDTO extends Request {
    @JsonProperty(value = "hotelName")
    private String hotelName;    // 酒店名称
    @JsonProperty(value = "city")
    private String city;         // 城市
    @JsonProperty(value = "address")
    private String address;      // 地址
    @JsonProperty(value = "phoneNumber")
    private String phoneNumber;  // 联系电话
    @JsonProperty(value = "level")
    private Integer level;       // 酒店星级（例如：5星）
}
