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
@ApiModel(value = "query order detail info parameter")
public class QueryOrderDetailRequestDTO extends Request {

  @NotEmpty(message = "Page number cannot be empty")
  @ApiModelProperty("page number")
  @JsonProperty("page")
  private Integer page;

  @NotEmpty(message = "Page size cannot be empty")
  @ApiModelProperty("page size")
  @JsonProperty("pageSize")
  private Integer pageSize;

  private String userId;
}
