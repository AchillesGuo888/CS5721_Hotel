package com.example.hotel.dto.request;

import com.example.hotel.dto.request.Base.Request;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@ApiModel("cancel order request parameter")
public class CheckInRequestDTO extends Request {

}
