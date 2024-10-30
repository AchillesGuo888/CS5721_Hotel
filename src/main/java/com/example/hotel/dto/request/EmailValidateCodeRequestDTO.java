package com.example.hotel.dto.request;



import com.example.hotel.dto.request.Base.Request;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author qzb
 * @date 2020/2/27
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@ApiModel("send verification code request")
public class EmailValidateCodeRequestDTO extends Request {

    @ApiModelProperty(value = "email",required = true)
    private String email;

    @ApiModelProperty(value = "verification code", hidden = true)
    private String code;
}
