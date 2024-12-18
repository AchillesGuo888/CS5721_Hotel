package com.example.hotel.entity;

import java.math.BigDecimal;
import java.util.Date;

public class RoomTypeInfo {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_type_info.id
     *
   * @mbg.generated Thu Nov 28 17:12:09 GMT 2024
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_type_info.hotel_id
     *
   * @mbg.generated Thu Nov 28 17:12:09 GMT 2024
     */
    private Long hotelId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_type_info.type_name
     *
   * @mbg.generated Thu Nov 28 17:12:09 GMT 2024
     */
    private String typeName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_type_info.update_time
     *
   * @mbg.generated Thu Nov 28 17:12:09 GMT 2024
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_type_info.create_time
     *
   * @mbg.generated Thu Nov 28 17:12:09 GMT 2024
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_type_info.is_deleted
     *
   * @mbg.generated Thu Nov 28 17:12:09 GMT 2024
     */
    private Byte isDeleted;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_type_info.type
     *
   * @mbg.generated Thu Nov 28 17:12:09 GMT 2024
     */
    private Byte type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_type_info.weekday_price
     *
   * @mbg.generated Thu Nov 28 17:12:09 GMT 2024
     */
    private BigDecimal weekdayPrice;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_type_info.weekend_price
     *
   * @mbg.generated Thu Nov 28 17:12:09 GMT 2024
     */
    private BigDecimal weekendPrice;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_type_info.room_count
     *
   * @mbg.generated Thu Nov 28 17:12:09 GMT 2024
     */
    private Integer roomCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_type_info.max_quantity
     *
   * @mbg.generated Thu Nov 28 17:12:09 GMT 2024
     */
    private Integer maxQuantity;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_type_info.ameneties
     *
   * @mbg.generated Thu Nov 28 17:12:09 GMT 2024
     */
    private String ameneties;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_type_info.id
     *
     * @return the value of room_type_info.id
     *
   * @mbg.generated Thu Nov 28 17:12:09 GMT 2024
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_type_info.id
     *
     * @param id the value for room_type_info.id
     *
   * @mbg.generated Thu Nov 28 17:12:09 GMT 2024
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_type_info.hotel_id
     *
     * @return the value of room_type_info.hotel_id
     *
   * @mbg.generated Thu Nov 28 17:12:09 GMT 2024
     */
    public Long getHotelId() {
        return hotelId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_type_info.hotel_id
     *
     * @param hotelId the value for room_type_info.hotel_id
     *
   * @mbg.generated Thu Nov 28 17:12:09 GMT 2024
     */
    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_type_info.type_name
     *
     * @return the value of room_type_info.type_name
     *
   * @mbg.generated Thu Nov 28 17:12:09 GMT 2024
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_type_info.type_name
     *
     * @param typeName the value for room_type_info.type_name
     *
   * @mbg.generated Thu Nov 28 17:12:09 GMT 2024
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_type_info.update_time
     *
     * @return the value of room_type_info.update_time
     *
   * @mbg.generated Thu Nov 28 17:12:09 GMT 2024
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_type_info.update_time
     *
     * @param updateTime the value for room_type_info.update_time
     *
   * @mbg.generated Thu Nov 28 17:12:09 GMT 2024
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_type_info.create_time
     *
     * @return the value of room_type_info.create_time
     *
   * @mbg.generated Thu Nov 28 17:12:09 GMT 2024
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_type_info.create_time
     *
     * @param createTime the value for room_type_info.create_time
     *
   * @mbg.generated Thu Nov 28 17:12:09 GMT 2024
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_type_info.is_deleted
     *
     * @return the value of room_type_info.is_deleted
     *
   * @mbg.generated Thu Nov 28 17:12:09 GMT 2024
     */
    public Byte getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_type_info.is_deleted
     *
     * @param isDeleted the value for room_type_info.is_deleted
     *
   * @mbg.generated Thu Nov 28 17:12:09 GMT 2024
     */
    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_type_info.type
     *
     * @return the value of room_type_info.type
     *
   * @mbg.generated Thu Nov 28 17:12:09 GMT 2024
     */
    public Byte getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_type_info.type
     *
     * @param type the value for room_type_info.type
     *
   * @mbg.generated Thu Nov 28 17:12:09 GMT 2024
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_type_info.weekday_price
     *
     * @return the value of room_type_info.weekday_price
     *
   * @mbg.generated Thu Nov 28 17:12:09 GMT 2024
     */
    public BigDecimal getWeekdayPrice() {
        return weekdayPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_type_info.weekday_price
     *
     * @param weekdayPrice the value for room_type_info.weekday_price
     *
   * @mbg.generated Thu Nov 28 17:12:09 GMT 2024
     */
    public void setWeekdayPrice(BigDecimal weekdayPrice) {
        this.weekdayPrice = weekdayPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_type_info.weekend_price
     *
     * @return the value of room_type_info.weekend_price
     *
   * @mbg.generated Thu Nov 28 17:12:09 GMT 2024
     */
    public BigDecimal getWeekendPrice() {
        return weekendPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_type_info.weekend_price
     *
     * @param weekendPrice the value for room_type_info.weekend_price
     *
   * @mbg.generated Thu Nov 28 17:12:09 GMT 2024
     */
    public void setWeekendPrice(BigDecimal weekendPrice) {
        this.weekendPrice = weekendPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_type_info.room_count
     *
     * @return the value of room_type_info.room_count
     *
   * @mbg.generated Thu Nov 28 17:12:09 GMT 2024
     */
    public Integer getRoomCount() {
        return roomCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_type_info.room_count
     *
     * @param roomCount the value for room_type_info.room_count
     *
   * @mbg.generated Thu Nov 28 17:12:09 GMT 2024
     */
    public void setRoomCount(Integer roomCount) {
        this.roomCount = roomCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_type_info.max_quantity
     *
     * @return the value of room_type_info.max_quantity
     *
   * @mbg.generated Thu Nov 28 17:12:09 GMT 2024
     */
    public Integer getMaxQuantity() {
        return maxQuantity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_type_info.max_quantity
     *
     * @param maxQuantity the value for room_type_info.max_quantity
     *
   * @mbg.generated Thu Nov 28 17:12:09 GMT 2024
     */
    public void setMaxQuantity(Integer maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_type_info.ameneties
     *
     * @return the value of room_type_info.ameneties
     *
   * @mbg.generated Thu Nov 28 17:12:09 GMT 2024
     */
    public String getAmeneties() {
        return ameneties;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_type_info.ameneties
     *
     * @param ameneties the value for room_type_info.ameneties
     *
   * @mbg.generated Thu Nov 28 17:12:09 GMT 2024
     */
    public void setAmeneties(String ameneties) {
        this.ameneties = ameneties;
    }
}