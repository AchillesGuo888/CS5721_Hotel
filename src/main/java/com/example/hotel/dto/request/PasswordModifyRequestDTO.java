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
@ApiModel("update password request")
public class PasswordModifyRequestDTO extends Request {

  @NotEmpty(message = "email cannot be empty")
  @ApiModelProperty(value = "email", required = true, example = "aaa@qq.com")
  @JsonProperty(value = "email")
  private String email;

  @NotEmpty(message = "new password cannot be empty")
  @ApiModelProperty(value = "new password", required = true, example = "123456")
  @JsonProperty(value = "newPassword")
  private String newPassword;

  @NotEmpty(message = "old password cannot be empty")
  @ApiModelProperty(value = "old password", required = true, example = "654321")
  @JsonProperty(value = "oldPassword")
  private String oldPassword;
}
