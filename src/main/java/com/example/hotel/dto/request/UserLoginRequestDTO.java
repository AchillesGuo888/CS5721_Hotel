package com.example.hotel.dto.request;

import com.example.hotel.dto.request.Base.Request;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@ApiModel("login request parameter")
public class UserLoginRequestDTO extends Request {
  @NotEmpty(message = "email cannot be empty")
  @ApiModelProperty(value = "email", required = true, example = "11@qq.com")
  private String email;

  @NotEmpty(message = "Password cannot be empty")
  @ApiModelProperty(value = "password", required = true, example = "123456")
  private String password;


}
