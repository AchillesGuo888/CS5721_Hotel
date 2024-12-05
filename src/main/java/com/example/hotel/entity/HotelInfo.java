package com.example.hotel.entity;

import java.time.LocalDateTime;

public class HotelInfo {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hotel_info.id
     *
     * @mbg.generated Thu Dec 05 17:40:38 GMT 2024
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hotel_info.hotel_name
     *
     * @mbg.generated Thu Dec 05 17:40:38 GMT 2024
     */
    private String hotelName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hotel_info.city
     *
     * @mbg.generated Thu Dec 05 17:40:38 GMT 2024
     */
    private String city;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hotel_info.address
     *
     * @mbg.generated Thu Dec 05 17:40:38 GMT 2024
     */
    private String address;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hotel_info.phone
     *
     * @mbg.generated Thu Dec 05 17:40:38 GMT 2024
     */
    private String phone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hotel_info.level
     *
     * @mbg.generated Thu Dec 05 17:40:38 GMT 2024
     */
    private Byte level;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hotel_info.update_time
     *
     * @mbg.generated Thu Dec 05 17:40:38 GMT 2024
     */
    private LocalDateTime updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hotel_info.create_time
     *
     * @mbg.generated Thu Dec 05 17:40:38 GMT 2024
     */
    private LocalDateTime createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column hotel_info.is_deleted
     *
     * @mbg.generated Thu Dec 05 17:40:38 GMT 2024
     */
    private Byte isDeleted;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hotel_info.id
     *
     * @return the value of hotel_info.id
     *
     * @mbg.generated Thu Dec 05 17:40:38 GMT 2024
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hotel_info.id
     *
     * @param id the value for hotel_info.id
     *
     * @mbg.generated Thu Dec 05 17:40:38 GMT 2024
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hotel_info.hotel_name
     *
     * @return the value of hotel_info.hotel_name
     *
     * @mbg.generated Thu Dec 05 17:40:38 GMT 2024
     */
    public String getHotelName() {
        return hotelName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hotel_info.hotel_name
     *
     * @param hotelName the value for hotel_info.hotel_name
     *
     * @mbg.generated Thu Dec 05 17:40:38 GMT 2024
     */
    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hotel_info.city
     *
     * @return the value of hotel_info.city
     *
     * @mbg.generated Thu Dec 05 17:40:38 GMT 2024
     */
    public String getCity() {
        return city;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hotel_info.city
     *
     * @param city the value for hotel_info.city
     *
     * @mbg.generated Thu Dec 05 17:40:38 GMT 2024
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hotel_info.address
     *
     * @return the value of hotel_info.address
     *
     * @mbg.generated Thu Dec 05 17:40:38 GMT 2024
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hotel_info.address
     *
     * @param address the value for hotel_info.address
     *
     * @mbg.generated Thu Dec 05 17:40:38 GMT 2024
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hotel_info.phone
     *
     * @return the value of hotel_info.phone
     *
     * @mbg.generated Thu Dec 05 17:40:38 GMT 2024
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hotel_info.phone
     *
     * @param phone the value for hotel_info.phone
     *
     * @mbg.generated Thu Dec 05 17:40:38 GMT 2024
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hotel_info.level
     *
     * @return the value of hotel_info.level
     *
     * @mbg.generated Thu Dec 05 17:40:38 GMT 2024
     */
    public Byte getLevel() {
        return level;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hotel_info.level
     *
     * @param level the value for hotel_info.level
     *
     * @mbg.generated Thu Dec 05 17:40:38 GMT 2024
     */
    public void setLevel(Byte level) {
        this.level = level;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hotel_info.update_time
     *
     * @return the value of hotel_info.update_time
     *
     * @mbg.generated Thu Dec 05 17:40:38 GMT 2024
     */
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hotel_info.update_time
     *
     * @param updateTime the value for hotel_info.update_time
     *
     * @mbg.generated Thu Dec 05 17:40:38 GMT 2024
     */
    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hotel_info.create_time
     *
     * @return the value of hotel_info.create_time
     *
     * @mbg.generated Thu Dec 05 17:40:38 GMT 2024
     */
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hotel_info.create_time
     *
     * @param createTime the value for hotel_info.create_time
     *
     * @mbg.generated Thu Dec 05 17:40:38 GMT 2024
     */
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column hotel_info.is_deleted
     *
     * @return the value of hotel_info.is_deleted
     *
     * @mbg.generated Thu Dec 05 17:40:38 GMT 2024
     */
    public Byte getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column hotel_info.is_deleted
     *
     * @param isDeleted the value for hotel_info.is_deleted
     *
     * @mbg.generated Thu Dec 05 17:40:38 GMT 2024
     */
    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }
}