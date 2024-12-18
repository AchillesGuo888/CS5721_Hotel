package com.example.hotel.entity;

import java.math.BigDecimal;
import java.util.Date;

public class RoomTypePrice {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_type_price.id
     *
     * @mbg.generated Thu Nov 28 23:25:50 IST 2024
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_type_price.room_type_id
     *
     * @mbg.generated Thu Nov 28 23:25:50 IST 2024
     */
    private Long roomTypeId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_type_price.update_time
     *
     * @mbg.generated Thu Nov 28 23:25:50 IST 2024
     */
    private Date updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_type_price.create_time
     *
     * @mbg.generated Thu Nov 28 23:25:50 IST 2024
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_type_price.is_deleted
     *
     * @mbg.generated Thu Nov 28 23:25:50 IST 2024
     */
    private Byte isDeleted;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_type_price.start_date
     *
     * @mbg.generated Thu Nov 28 23:25:50 IST 2024
     */
    private Date startDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_type_price.end_date
     *
     * @mbg.generated Thu Nov 28 23:25:50 IST 2024
     */
    private Date endDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column room_type_price.price
     *
     * @mbg.generated Thu Nov 28 23:25:50 IST 2024
     */
    private BigDecimal price;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_type_price.id
     *
     * @return the value of room_type_price.id
     *
     * @mbg.generated Thu Nov 28 23:25:50 IST 2024
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_type_price.id
     *
     * @param id the value for room_type_price.id
     *
     * @mbg.generated Thu Nov 28 23:25:50 IST 2024
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_type_price.room_type_id
     *
     * @return the value of room_type_price.room_type_id
     *
     * @mbg.generated Thu Nov 28 23:25:50 IST 2024
     */
    public Long getRoomTypeId() {
        return roomTypeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_type_price.room_type_id
     *
     * @param roomTypeId the value for room_type_price.room_type_id
     *
     * @mbg.generated Thu Nov 28 23:25:50 IST 2024
     */
    public void setRoomTypeId(Long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_type_price.update_time
     *
     * @return the value of room_type_price.update_time
     *
     * @mbg.generated Thu Nov 28 23:25:50 IST 2024
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_type_price.update_time
     *
     * @param updateTime the value for room_type_price.update_time
     *
     * @mbg.generated Thu Nov 28 23:25:50 IST 2024
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_type_price.create_time
     *
     * @return the value of room_type_price.create_time
     *
     * @mbg.generated Thu Nov 28 23:25:50 IST 2024
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_type_price.create_time
     *
     * @param createTime the value for room_type_price.create_time
     *
     * @mbg.generated Thu Nov 28 23:25:50 IST 2024
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_type_price.is_deleted
     *
     * @return the value of room_type_price.is_deleted
     *
     * @mbg.generated Thu Nov 28 23:25:50 IST 2024
     */
    public Byte getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_type_price.is_deleted
     *
     * @param isDeleted the value for room_type_price.is_deleted
     *
     * @mbg.generated Thu Nov 28 23:25:50 IST 2024
     */
    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_type_price.start_date
     *
     * @return the value of room_type_price.start_date
     *
     * @mbg.generated Thu Nov 28 23:25:50 IST 2024
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_type_price.start_date
     *
     * @param startDate the value for room_type_price.start_date
     *
     * @mbg.generated Thu Nov 28 23:25:50 IST 2024
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_type_price.end_date
     *
     * @return the value of room_type_price.end_date
     *
     * @mbg.generated Thu Nov 28 23:25:50 IST 2024
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_type_price.end_date
     *
     * @param endDate the value for room_type_price.end_date
     *
     * @mbg.generated Thu Nov 28 23:25:50 IST 2024
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column room_type_price.price
     *
     * @return the value of room_type_price.price
     *
     * @mbg.generated Thu Nov 28 23:25:50 IST 2024
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column room_type_price.price
     *
     * @param price the value for room_type_price.price
     *
     * @mbg.generated Thu Nov 28 23:25:50 IST 2024
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}