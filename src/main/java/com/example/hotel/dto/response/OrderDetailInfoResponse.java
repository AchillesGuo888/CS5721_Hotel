package com.example.hotel.dto.response;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.pool2.BaseObject;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@ApiModel("query order detail info result")
public class OrderDetailInfoResponse extends BaseObject {

}
