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
@ApiModel("Register request parameter")
public class RegisterRequestDTO extends Request {
  @NotEmpty(message = "Username cannot be empty")
  @ApiModelProperty(value = "username", required = true, example = "Achilles")
  private String userName;

  @NotEmpty(message = "Password cannot be empty")
  @ApiModelProperty(value = "password", required = true, example = "123456")
  private String password;

  @ApiModelProperty(value = "phone", example = "8339012345")
  private String phone;

  @NotEmpty(message = "email cannot be empty")
  @ApiModelProperty(value = "email", required = true, example = "11@qq.com")
  private String email;

  @NotEmpty(message = "verification code cannot be empty")
  @ApiModelProperty(value = "code", required = true, example = "952777")
  private String code;

  @ApiModelProperty(value = "gender(0:male, 1:female, 2:secret)", example = "0")
  private Integer gender;

  @ApiModelProperty(value = "address", example = "Dublin")
  private String address;
}
