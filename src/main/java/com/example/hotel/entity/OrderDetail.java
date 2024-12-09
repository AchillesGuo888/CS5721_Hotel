package com.example.hotel.entity;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class OrderDetail {

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * order_detail.id
   *
   * @mbg.generated Fri Nov 29 00:34:27 GMT 2024
   */
  private Long id;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * order_detail.order_id
   *
   * @mbg.generated Fri Nov 29 00:34:27 GMT 2024
   */
  private Long orderId;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * order_detail.user_id
   *
   * @mbg.generated Fri Nov 29 00:34:27 GMT 2024
   */
  private String userId;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * order_detail.room_number
   *
   * @mbg.generated Fri Nov 29 00:34:27 GMT 2024
   */
  private String roomNumber;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * order_detail.price_difference
   *
   * @mbg.generated Fri Nov 29 00:34:27 GMT 2024
   */
  private BigDecimal priceDifference;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * order_detail.update_time
   *
   * @mbg.generated Fri Nov 29 00:34:27 GMT 2024
   */
  private LocalDateTime updateTime;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * order_detail.create_time
   *
   * @mbg.generated Fri Nov 29 00:34:27 GMT 2024
   */
  private LocalDateTime createTime;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * order_detail.is_deleted
   *
   * @mbg.generated Fri Nov 29 00:34:27 GMT 2024
   */
  private Byte isDeleted;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * order_detail.remark
   *
   * @mbg.generated Fri Nov 29 00:34:27 GMT 2024
   */
  private String remark;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * order_detail.room_type_id
   *
   * @mbg.generated Fri Nov 29 00:34:27 GMT 2024
   */
  private Long roomTypeId;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * order_detail.original_room_type_id
   *
   * @mbg.generated Fri Nov 29 00:34:27 GMT 2024
   */
  private Long originalRoomTypeId;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * order_detail.status
   *
   * @mbg.generated Fri Nov 29 00:34:27 GMT 2024
   */
  private Byte status;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * order_detail.price
   *
   * @mbg.generated Fri Nov 29 00:34:27 GMT 2024
   */
  private BigDecimal price;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * order_detail.change_type
   *
   * @mbg.generated Fri Nov 29 00:34:27 GMT 2024
   */
  private Byte changeType;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * order_detail.change_date
   *
   * @mbg.generated Fri Nov 29 00:34:27 GMT 2024
   */
  private LocalDate changeDate;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column
   * order_detail.guest_name
   *
   * @mbg.generated Fri Nov 29 00:34:27 GMT 2024
   */
  private String guestName;


  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column order_detail.id
   *
   * @return the value of order_detail.id
   * @mbg.generated Fri Nov 29 00:34:27 GMT 2024
   */
  public Long getId() {
    return id;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column order_detail.id
   *
   * @param id the value for order_detail.id
   * @mbg.generated Fri Nov 29 00:34:27 GMT 2024
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column order_detail.order_id
   *
   * @return the value of order_detail.order_id
   * @mbg.generated Fri Nov 29 00:34:27 GMT 2024
   */
  public Long getOrderId() {
    return orderId;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column order_detail.order_id
   *
   * @param orderId the value for order_detail.order_id
   * @mbg.generated Fri Nov 29 00:34:27 GMT 2024
   */
  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column order_detail.user_id
   *
   * @return the value of order_detail.user_id
   * @mbg.generated Fri Nov 29 00:34:27 GMT 2024
   */
  public String getUserId() {
    return userId;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column order_detail.user_id
   *
   * @param userId the value for order_detail.user_id
   * @mbg.generated Fri Nov 29 00:34:27 GMT 2024
   */
  public void setUserId(String userId) {
    this.userId = userId;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column order_detail.room_number
   *
   * @return the value of order_detail.room_number
   * @mbg.generated Fri Nov 29 00:34:27 GMT 2024
   */
  public String getRoomNumber() {
    return roomNumber;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column order_detail.room_number
   *
   * @param roomNumber the value for order_detail.room_number
   * @mbg.generated Fri Nov 29 00:34:27 GMT 2024
   */
  public void setRoomNumber(String roomNumber) {
    this.roomNumber = roomNumber;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column order_detail.price_difference
   *
   * @return the value of order_detail.price_difference
   * @mbg.generated Fri Nov 29 00:34:27 GMT 2024
   */
  public BigDecimal getPriceDifference() {
    return priceDifference;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column order_detail.price_difference
   *
   * @param priceDifference the value for order_detail.price_difference
   * @mbg.generated Fri Nov 29 00:34:27 GMT 2024
   */
  public void setPriceDifference(BigDecimal priceDifference) {
    this.priceDifference = priceDifference;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column order_detail.update_time
   *
   * @return the value of order_detail.update_time
   * @mbg.generated Fri Nov 29 00:34:27 GMT 2024
   */
  public LocalDateTime getUpdateTime() {
    return updateTime;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column order_detail.update_time
   *
   * @param updateTime the value for order_detail.update_time
   * @mbg.generated Fri Nov 29 00:34:27 GMT 2024
   */
  public void setUpdateTime(LocalDateTime updateTime) {
    this.updateTime = updateTime;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column order_detail.create_time
   *
   * @return the value of order_detail.create_time
   * @mbg.generated Fri Nov 29 00:34:27 GMT 2024
   */
  public LocalDateTime getCreateTime() {
    return createTime;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column order_detail.create_time
   *
   * @param createTime the value for order_detail.create_time
   * @mbg.generated Fri Nov 29 00:34:27 GMT 2024
   */
  public void setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column order_detail.is_deleted
   *
   * @return the value of order_detail.is_deleted
   * @mbg.generated Fri Nov 29 00:34:27 GMT 2024
   */
  public Byte getIsDeleted() {
    return isDeleted;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column order_detail.is_deleted
   *
   * @param isDeleted the value for order_detail.is_deleted
   * @mbg.generated Fri Nov 29 00:34:27 GMT 2024
   */
  public void setIsDeleted(Byte isDeleted) {
    this.isDeleted = isDeleted;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column order_detail.remark
   *
   * @return the value of order_detail.remark
   * @mbg.generated Fri Nov 29 00:34:27 GMT 2024
   */
  public String getRemark() {
    return remark;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column order_detail.remark
   *
   * @param remark the value for order_detail.remark
   * @mbg.generated Fri Nov 29 00:34:27 GMT 2024
   */
  public void setRemark(String remark) {
    this.remark = remark;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column order_detail.room_type_id
   *
   * @return the value of order_detail.room_type_id
   * @mbg.generated Fri Nov 29 00:34:27 GMT 2024
   */
  public Long getRoomTypeId() {
    return roomTypeId;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column order_detail.room_type_id
   *
   * @param roomTypeId the value for order_detail.room_type_id
   * @mbg.generated Fri Nov 29 00:34:27 GMT 2024
   */
  public void setRoomTypeId(Long roomTypeId) {
    this.roomTypeId = roomTypeId;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column order_detail.original_room_type_id
   *
   * @return the value of order_detail.original_room_type_id
   * @mbg.generated Fri Nov 29 00:34:27 GMT 2024
   */
  public Long getOriginalRoomTypeId() {
    return originalRoomTypeId;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column order_detail.original_room_type_id
   *
   * @param originalRoomTypeId the value for order_detail.original_room_type_id
   * @mbg.generated Fri Nov 29 00:34:27 GMT 2024
   */
  public void setOriginalRoomTypeId(Long originalRoomTypeId) {
    this.originalRoomTypeId = originalRoomTypeId;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column order_detail.status
   *
   * @return the value of order_detail.status
   * @mbg.generated Fri Nov 29 00:34:27 GMT 2024
   */
  public Byte getStatus() {
    return status;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column order_detail.status
   *
   * @param status the value for order_detail.status
   * @mbg.generated Fri Nov 29 00:34:27 GMT 2024
   */
  public void setStatus(Byte status) {
    this.status = status;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column order_detail.price
   *
   * @return the value of order_detail.price
   * @mbg.generated Fri Nov 29 00:34:27 GMT 2024
   */
  public BigDecimal getPrice() {
    return price;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column order_detail.price
   *
   * @param price the value for order_detail.price
   * @mbg.generated Fri Nov 29 00:34:27 GMT 2024
   */
  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column order_detail.change_type
   *
   * @return the value of order_detail.change_type
   * @mbg.generated Fri Nov 29 00:34:27 GMT 2024
   */
  public Byte getChangeType() {
    return changeType;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column order_detail.change_type
   *
   * @param changeType the value for order_detail.change_type
   * @mbg.generated Fri Nov 29 00:34:27 GMT 2024
   */
  public void setChangeType(Byte changeType) {
    this.changeType = changeType;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column order_detail.change_date
   *
   * @return the value of order_detail.change_date
   * @mbg.generated Fri Nov 29 00:34:27 GMT 2024
   */
  public LocalDate getChangeDate() {
    return changeDate;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column order_detail.change_date
   *
   * @param changeDate the value for order_detail.change_date
   * @mbg.generated Fri Nov 29 00:34:27 GMT 2024
   */
  public void setChangeDate(LocalDate changeDate) {
    this.changeDate = changeDate;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database
   * column order_detail.guest_name
   *
   * @return the value of order_detail.guest_name
   * @mbg.generated Fri Nov 29 00:34:27 GMT 2024
   */
  public String getGuestName() {
    return guestName;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database
   * column order_detail.guest_name
   *
   * @param guestName the value for order_detail.guest_name
   * @mbg.generated Fri Nov 29 00:34:27 GMT 2024
   */
  public void setGuestName(String guestName) {
    this.guestName = guestName;
  }
}