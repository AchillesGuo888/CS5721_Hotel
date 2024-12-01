package com.example.hotel.vo;

import java.io.Serializable;
import lombok.Data;


/**
 * @author example
 */
@Data
public class PageVo implements Serializable {

  private static final long serialVersionUID = 1L;

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }


  /**
   * 页号
   */
  private long page = 1;

  /**
   * 页大小
   */
  private long size = 10;

  /**
   * 排序字段
   */
  private String sort = "create_time";

  /**
   * 排序方式 asc/desc
   */
  private String order = "desc";

  /**
   * 当前页码
   */
  private long current;

  /**
   * 总数
   */
  private long total;

  /**
   * 页数
   */
  private long pages;


  public PageVo() {
  }

  public PageVo(int page, int size) {
    this.page = page;
    this.size = size;
  }

  public PageVo(int page, int size, String sort, String order) {
    this.page = page;
    this.size = size;
    this.sort = sort;
    this.order = order;
  }

  public long getPage() {
    return page;
  }

  public void setPage(long page) {
    this.page = page;
  }

  public long getSize() {
    return size;
  }

  public void setSize(long size) {
    this.size = size;
  }

  public String getSort() {
    return sort;
  }

  public void setSort(String sort) {
    this.sort = sort;
  }

  public String getOrder() {
    return order;
  }

  public void setOrder(String order) {
    this.order = order;
  }

  public long getCurrent() {
    return current;
  }

  public void setCurrent(long current) {
    this.current = current;
  }

  public long getTotal() {
    return total;
  }

  public void setTotal(long total) {
    this.total = total;
  }

  public long getPages() {
    return pages;
  }

  public void setPages(long pages) {
    this.pages = pages;
  }
}
