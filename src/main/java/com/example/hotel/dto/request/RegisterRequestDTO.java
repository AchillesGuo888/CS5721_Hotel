package com.example.hotel.dto.request;

import com.example.hotel.dto.request.Base.Request;
import com.fasterxml.jackson.annotation.JsonProperty;
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
  @JsonProperty(value = "userName")
  private String userName;

  @NotEmpty(message = "Password cannot be empty")
  @ApiModelProperty(value = "password", required = true, example = "123456")
  @JsonProperty(value = "password")
  private String password;

  @ApiModelProperty(value = "phone", example = "8339012345")
  @JsonProperty(value = "phone")
  private String phone;

  @NotEmpty(message = "email cannot be empty")
  @ApiModelProperty(value = "email", required = true, example = "11@qq.com")
  @JsonProperty(value = "email")
  private String email;

  @NotEmpty(message = "verification code cannot be empty")
  @ApiModelProperty(value = "code", required = true, example = "952777")
  @JsonProperty(value = "code")
  private String code;

  @ApiModelProperty(value = "gender(0:male, 1:female, 2:secret)", example = "0")
  @JsonProperty(value = "gender")
  private Integer gender;

  @ApiModelProperty(value = "address", example = "Dublin")
  @JsonProperty(value = "address")
  private String address;
}
