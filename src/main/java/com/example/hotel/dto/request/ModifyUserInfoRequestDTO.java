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
@ApiModel(value = "modify user info parameter")
public class ModifyUserInfoRequestDTO extends Request {
  @NotEmpty(message = "Username cannot be empty")
  @ApiModelProperty(value = "username", required = true, example = "Achilles")
  private String username;

  @NotEmpty(message = "phone cannot be empty")
  @ApiModelProperty(value = "phone", required = true, example = "8339012345")
  private String phone;

  @ApiModelProperty(value = "gender(0:male, 1:female, 2:secret)", example = "0")
  private Integer gender;

  @ApiModelProperty(value = "address", example = "Dublin")
  private String address;
  @ApiModelProperty(hidden = true)
  private String userId;
}
