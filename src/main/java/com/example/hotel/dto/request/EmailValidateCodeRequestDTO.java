package com.example.hotel.dto.request;


import com.example.hotel.dto.request.Base.Request;
import com.fasterxml.jackson.annotation.JsonProperty;
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

  private static final long serialVersionUID = -3553410229263995608L;

  @ApiModelProperty(value = "email", required = true)
  @JsonProperty(value = "email")
  private String email;

  @ApiModelProperty(value = "verification code", hidden = true)
  private String code;
}
