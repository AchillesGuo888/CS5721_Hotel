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
@ApiModel(value = "modify order info parameter")
public class ModifyOrderInfoRequestDTO extends Request {

  @NotEmpty(message = "orderId cannot be empty")
  @ApiModelProperty(value = "orderId", required = true, example = "1")
  @JsonProperty(value = "orderId")
  private Long orderId;

  @NotEmpty(message = "contact name cannot be empty")
  @ApiModelProperty(value = "contactName", required = true, example = "Shane")
  @JsonProperty(value = "contactName")
  private String contactName;

  @NotEmpty(message = "contact phone cannot be empty")
  @ApiModelProperty(value = "contactPhone", required = true, example = "838901234")
  @JsonProperty(value = "contactPhone")
  private String contactPhone;

}
