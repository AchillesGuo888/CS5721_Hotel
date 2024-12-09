package com.example.hotel.dto.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@Data
@ToString
public class RoomTypePriceDTO implements Serializable {
    private static final long serialVersionUID = -8242580423324288123L;

    @JsonProperty("roomTypeId")
    private Long roomTypeId;
    @JsonProperty("updateTime")
    private Date updateTime;
    @JsonProperty("createTime")
    private Date createTime;
    @JsonProperty("isDeleted")
    private Byte isDeleted;
    @JsonProperty("startDate")
    private Date startDate;
    @JsonProperty("endDate")
    private Date endDate;
    @JsonProperty("price")
    private BigDecimal price;
}
