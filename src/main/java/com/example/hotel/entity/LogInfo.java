package com.example.hotel.entity;

import java.time.LocalDateTime;

public class LogInfo {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column log_info.id
     *
     * @mbg.generated Sat Dec 07 15:57:00 GMT 2024
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column log_info.url
     *
     * @mbg.generated Sat Dec 07 15:57:00 GMT 2024
     */
    private String url;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column log_info.body
     *
     * @mbg.generated Sat Dec 07 15:57:00 GMT 2024
     */
    private String body;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column log_info.header
     *
     * @mbg.generated Sat Dec 07 15:57:00 GMT 2024
     */
    private String header;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column log_info.create_time
     *
     * @mbg.generated Sat Dec 07 15:57:00 GMT 2024
     */
    private LocalDateTime createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column log_info.message
     *
     * @mbg.generated Sat Dec 07 15:57:00 GMT 2024
     */
    private String message;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column log_info.id
     *
     * @return the value of log_info.id
     *
     * @mbg.generated Sat Dec 07 15:57:00 GMT 2024
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column log_info.id
     *
     * @param id the value for log_info.id
     *
     * @mbg.generated Sat Dec 07 15:57:00 GMT 2024
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column log_info.url
     *
     * @return the value of log_info.url
     *
     * @mbg.generated Sat Dec 07 15:57:00 GMT 2024
     */
    public String getUrl() {
        return url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column log_info.url
     *
     * @param url the value for log_info.url
     *
     * @mbg.generated Sat Dec 07 15:57:00 GMT 2024
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column log_info.body
     *
     * @return the value of log_info.body
     *
     * @mbg.generated Sat Dec 07 15:57:00 GMT 2024
     */
    public String getBody() {
        return body;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column log_info.body
     *
     * @param body the value for log_info.body
     *
     * @mbg.generated Sat Dec 07 15:57:00 GMT 2024
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column log_info.header
     *
     * @return the value of log_info.header
     *
     * @mbg.generated Sat Dec 07 15:57:00 GMT 2024
     */
    public String getHeader() {
        return header;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column log_info.header
     *
     * @param header the value for log_info.header
     *
     * @mbg.generated Sat Dec 07 15:57:00 GMT 2024
     */
    public void setHeader(String header) {
        this.header = header;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column log_info.create_time
     *
     * @return the value of log_info.create_time
     *
     * @mbg.generated Sat Dec 07 15:57:00 GMT 2024
     */
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column log_info.create_time
     *
     * @param createTime the value for log_info.create_time
     *
     * @mbg.generated Sat Dec 07 15:57:00 GMT 2024
     */
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column log_info.message
     *
     * @return the value of log_info.message
     *
     * @mbg.generated Sat Dec 07 15:57:00 GMT 2024
     */
    public String getMessage() {
        return message;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column log_info.message
     *
     * @param message the value for log_info.message
     *
     * @mbg.generated Sat Dec 07 15:57:00 GMT 2024
     */
    public void setMessage(String message) {
        this.message = message;
    }
}