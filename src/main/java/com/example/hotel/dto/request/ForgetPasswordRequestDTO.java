package com.example.hotel.dto.request;

import com.example.hotel.dto.request.Base.Request;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@ApiModel("forget password request")
public class ForgetPasswordRequestDTO extends Request {
    private static final long serialVersionUID = -725027636856828563L;

    @ApiModelProperty(value = "email",required = true, example = "aaa@qq.com")
    private String email;
    @ApiModelProperty(value = "code", required = true, example = "898989")
    private String code;
    @ApiModelProperty(value = "new password", required = true, example = "123456")
    private String newPassword;
    @ApiModelProperty(value = "confirm password", required = true, example = "123456")
    private String confirmPassword;


}
