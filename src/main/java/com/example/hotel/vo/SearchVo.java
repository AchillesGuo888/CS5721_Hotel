package com.example.hotel.vo;

import java.io.Serializable;
import lombok.Data;

/**
 * @author example
 */
@Data
public class SearchVo implements Serializable {

  /**
   * 起始日期
   */
  private String startDate;

  /**
   * 结束日期
   */
  private String endDate;

  public String getStartDate() {
    return startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public String getEndDate() {
    return endDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }
}
