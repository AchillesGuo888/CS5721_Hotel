package com.example.hotel.dto.request;

import com.example.hotel.dto.request.Base.Request;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@ApiModel(value = "query order detail info parameter")
public class QueryOrderListRequestDTO extends Request {

}
