package com.example.hotel.dto.response;

import com.example.hotel.common.base.ResponseResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ClassName: Update info response
 * @author: rui guo
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@ApiModel("update info result")
public class UpdateInfoResponse extends ResponseResult {

  private static final long serialVersionUID = -6808963525093889898L;

  @ApiModelProperty("change result")
  private Boolean result;
}
