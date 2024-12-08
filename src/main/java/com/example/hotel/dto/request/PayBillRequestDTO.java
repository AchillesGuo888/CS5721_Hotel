package com.example.hotel.dto.request;

import com.example.hotel.dto.request.Base.Request;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import javax.print.DocFlavor;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@ApiModel("pay room bill request")
public class PayBillRequestDTO extends Request {

  @NotEmpty(message = "card number cannot be empty")
  @ApiModelProperty(value = "cardNumber",required = true, example = "111111111111111")
  @JsonProperty(value = "cardNumber")
  private String cardNumber;

  @NotEmpty(message = "CVV number cannot be empty")
  @ApiModelProperty(value = "CVV",required = true, example = "768")
  @JsonProperty(value = "CVV")
  private Integer CVV;

  @NotEmpty(message = "name cannot be empty")
  @ApiModelProperty(value = "name",required = true, example = "zhangsan")
  @JsonProperty(value = "name")
  private String name;

  @NotEmpty(message = "expires end number cannot be empty")
  @ApiModelProperty(value = "expiresEnd",required = true, example = "07/29")
  @JsonProperty(value = "expiresEnd")
  private String expiresEnd;

  @NotEmpty(message = "order id cannot be empty")
  @ApiModelProperty(value = "orderId",required = true, example = "9527")
  @JsonProperty(value = "orderId")
  private Long orderId;

  @NotEmpty(message = "amount cannot be empty")
  @ApiModelProperty(value = "amount",required = true, example = "12345.67")
  @JsonProperty(value = "amount")
  private BigDecimal amount;

  @NotEmpty(message = "currency cannot be empty")
  @ApiModelProperty(value = "currency",required = true, example = "EUR")
  @JsonProperty(value = "currency")
  private String currency;

  @ApiModelProperty(value = "payType(0:pay bill,1: pay price difference)", example = "1")
  @JsonProperty(value = "payType")
  private Byte payType;

}
