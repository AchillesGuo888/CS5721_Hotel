package com.example.hotel.dto.response;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.pool2.BaseObject;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@ApiModel("query hotel list result(with price)")
public class AvailableHotelResponse extends BaseObject {
    public AvailableHotelResponse() {
        // 初始化逻辑，如果有需要的话
    }
}
