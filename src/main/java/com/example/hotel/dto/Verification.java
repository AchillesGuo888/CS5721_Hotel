package com.example.hotel.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
/**
 * verification code body
 */

public class Verification {

  public String code;
  private LocalDateTime createTime;

}
