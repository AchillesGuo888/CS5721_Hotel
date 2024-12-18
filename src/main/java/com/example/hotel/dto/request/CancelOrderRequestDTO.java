package com.example.hotel.dto.request;

import com.example.hotel.dto.request.Base.Request;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@ApiModel("cancel order request parameter")
public class CancelOrderRequestDTO extends Request {

    @ApiModelProperty(value = "Order ID", required = true, example = "12345")
    private Long orderId;

    @ApiModelProperty(value = "Reason for cancellation", required = false, example = "Change of plans")
    private String cancelReason;

    @ApiModelProperty(value = "Room Numbers to cancel (if null, cancel entire order)")
    private Long roomNumber;


    private int isApproved;  // Whether the audit is passed 0 means failed, 1 means passed

}
