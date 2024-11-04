package com.example.hotel.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.pool2.BaseObject;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@ApiModel("query user info result")
public class UserInfoResponse extends BaseObject {
  private static final long serialVersionUID = -6808963525093889898L;

  @ApiModelProperty("email")
  private String email;

  @ApiModelProperty("phone")
  private String phone;

  @ApiModelProperty("userName")
  private String userName;

  @ApiModelProperty("memberShipDesc")
  private String memberShipDesc;

  @ApiModelProperty("gender")
  private Integer gender;

  @ApiModelProperty("genderDesc")
  private String genderDesc;

}
