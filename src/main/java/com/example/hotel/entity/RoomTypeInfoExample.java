package com.example.hotel.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RoomTypeInfoExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table room_type_info
     *
   * @mbg.generated Thu Nov 28 17:12:09 GMT 2024
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table room_type_info
     *
   * @mbg.generated Thu Nov 28 17:12:09 GMT 2024
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table room_type_info
     *
   * @mbg.generated Thu Nov 28 17:12:09 GMT 2024
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_type_info
     *
   * @mbg.generated Thu Nov 28 17:12:09 GMT 2024
     */

     private Integer limit;
    private Integer offset;

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
    
    public RoomTypeInfoExample() {
        oredCriteria = new ArrayList<>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_type_info
     *
   * @mbg.generated Thu Nov 28 17:12:09 GMT 2024
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_type_info
     *
   * @mbg.generated Thu Nov 28 17:12:09 GMT 2024
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_type_info
     *
   * @mbg.generated Thu Nov 28 17:12:09 GMT 2024
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_type_info
     *
   * @mbg.generated Thu Nov 28 17:12:09 GMT 2024
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_type_info
     *
   * @mbg.generated Thu Nov 28 17:12:09 GMT 2024
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_type_info
     *
   * @mbg.generated Thu Nov 28 17:12:09 GMT 2024
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_type_info
     *
   * @mbg.generated Thu Nov 28 17:12:09 GMT 2024
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_type_info
     *
   * @mbg.generated Thu Nov 28 17:12:09 GMT 2024
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_type_info
     *
   * @mbg.generated Thu Nov 28 17:12:09 GMT 2024
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_type_info
     *
   * @mbg.generated Thu Nov 28 17:12:09 GMT 2024
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table room_type_info
     *
   * @mbg.generated Thu Nov 28 17:12:09 GMT 2024
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andHotelIdIsNull() {
            addCriterion("hotel_id is null");
            return (Criteria) this;
        }

        public Criteria andHotelIdIsNotNull() {
            addCriterion("hotel_id is not null");
            return (Criteria) this;
        }

        public Criteria andHotelIdEqualTo(Long value) {
            addCriterion("hotel_id =", value, "hotelId");
            return (Criteria) this;
        }

        public Criteria andHotelIdNotEqualTo(Long value) {
            addCriterion("hotel_id <>", value, "hotelId");
            return (Criteria) this;
        }

        public Criteria andHotelIdGreaterThan(Long value) {
            addCriterion("hotel_id >", value, "hotelId");
            return (Criteria) this;
        }

        public Criteria andHotelIdGreaterThanOrEqualTo(Long value) {
            addCriterion("hotel_id >=", value, "hotelId");
            return (Criteria) this;
        }

        public Criteria andHotelIdLessThan(Long value) {
            addCriterion("hotel_id <", value, "hotelId");
            return (Criteria) this;
        }

        public Criteria andHotelIdLessThanOrEqualTo(Long value) {
            addCriterion("hotel_id <=", value, "hotelId");
            return (Criteria) this;
        }

        public Criteria andHotelIdIn(List<Long> values) {
            addCriterion("hotel_id in", values, "hotelId");
            return (Criteria) this;
        }

        public Criteria andHotelIdNotIn(List<Long> values) {
            addCriterion("hotel_id not in", values, "hotelId");
            return (Criteria) this;
        }

        public Criteria andHotelIdBetween(Long value1, Long value2) {
            addCriterion("hotel_id between", value1, value2, "hotelId");
            return (Criteria) this;
        }

        public Criteria andHotelIdNotBetween(Long value1, Long value2) {
            addCriterion("hotel_id not between", value1, value2, "hotelId");
            return (Criteria) this;
        }

        public Criteria andTypeNameIsNull() {
            addCriterion("type_name is null");
            return (Criteria) this;
        }

        public Criteria andTypeNameIsNotNull() {
            addCriterion("type_name is not null");
            return (Criteria) this;
        }

        public Criteria andTypeNameEqualTo(String value) {
            addCriterion("type_name =", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotEqualTo(String value) {
            addCriterion("type_name <>", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameGreaterThan(String value) {
            addCriterion("type_name >", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("type_name >=", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameLessThan(String value) {
            addCriterion("type_name <", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameLessThanOrEqualTo(String value) {
            addCriterion("type_name <=", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameLike(String value) {
            addCriterion("type_name like", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotLike(String value) {
            addCriterion("type_name not like", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameIn(List<String> values) {
            addCriterion("type_name in", values, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotIn(List<String> values) {
            addCriterion("type_name not in", values, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameBetween(String value1, String value2) {
            addCriterion("type_name between", value1, value2, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotBetween(String value1, String value2) {
            addCriterion("type_name not between", value1, value2, "typeName");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(LocalDateTime value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(LocalDateTime value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(LocalDateTime value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(LocalDateTime value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<LocalDateTime> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<LocalDateTime> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(LocalDateTime value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(LocalDateTime value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(LocalDateTime value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(LocalDateTime value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<LocalDateTime> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<LocalDateTime> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIsNull() {
            addCriterion("is_deleted is null");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIsNotNull() {
            addCriterion("is_deleted is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeletedEqualTo(Byte value) {
            addCriterion("is_deleted =", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotEqualTo(Byte value) {
            addCriterion("is_deleted <>", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedGreaterThan(Byte value) {
            addCriterion("is_deleted >", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_deleted >=", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedLessThan(Byte value) {
            addCriterion("is_deleted <", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedLessThanOrEqualTo(Byte value) {
            addCriterion("is_deleted <=", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIn(List<Byte> values) {
            addCriterion("is_deleted in", values, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotIn(List<Byte> values) {
            addCriterion("is_deleted not in", values, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedBetween(Byte value1, Byte value2) {
            addCriterion("is_deleted between", value1, value2, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotBetween(Byte value1, Byte value2) {
            addCriterion("is_deleted not between", value1, value2, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Byte value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Byte value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Byte value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Byte value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Byte value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Byte> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Byte> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Byte value1, Byte value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andWeekdayPriceIsNull() {
            addCriterion("weekday_price is null");
            return (Criteria) this;
        }

        public Criteria andWeekdayPriceIsNotNull() {
            addCriterion("weekday_price is not null");
            return (Criteria) this;
        }

        public Criteria andWeekdayPriceEqualTo(BigDecimal value) {
            addCriterion("weekday_price =", value, "weekdayPrice");
            return (Criteria) this;
        }

        public Criteria andWeekdayPriceNotEqualTo(BigDecimal value) {
            addCriterion("weekday_price <>", value, "weekdayPrice");
            return (Criteria) this;
        }

        public Criteria andWeekdayPriceGreaterThan(BigDecimal value) {
            addCriterion("weekday_price >", value, "weekdayPrice");
            return (Criteria) this;
        }

        public Criteria andWeekdayPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("weekday_price >=", value, "weekdayPrice");
            return (Criteria) this;
        }

        public Criteria andWeekdayPriceLessThan(BigDecimal value) {
            addCriterion("weekday_price <", value, "weekdayPrice");
            return (Criteria) this;
        }

        public Criteria andWeekdayPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("weekday_price <=", value, "weekdayPrice");
            return (Criteria) this;
        }

        public Criteria andWeekdayPriceIn(List<BigDecimal> values) {
            addCriterion("weekday_price in", values, "weekdayPrice");
            return (Criteria) this;
        }

        public Criteria andWeekdayPriceNotIn(List<BigDecimal> values) {
            addCriterion("weekday_price not in", values, "weekdayPrice");
            return (Criteria) this;
        }

        public Criteria andWeekdayPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("weekday_price between", value1, value2, "weekdayPrice");
            return (Criteria) this;
        }

        public Criteria andWeekdayPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("weekday_price not between", value1, value2, "weekdayPrice");
            return (Criteria) this;
        }

        public Criteria andWeekendPriceIsNull() {
            addCriterion("weekend_price is null");
            return (Criteria) this;
        }

        public Criteria andWeekendPriceIsNotNull() {
            addCriterion("weekend_price is not null");
            return (Criteria) this;
        }

        public Criteria andWeekendPriceEqualTo(BigDecimal value) {
            addCriterion("weekend_price =", value, "weekendPrice");
            return (Criteria) this;
        }

        public Criteria andWeekendPriceNotEqualTo(BigDecimal value) {
            addCriterion("weekend_price <>", value, "weekendPrice");
            return (Criteria) this;
        }

        public Criteria andWeekendPriceGreaterThan(BigDecimal value) {
            addCriterion("weekend_price >", value, "weekendPrice");
            return (Criteria) this;
        }

        public Criteria andWeekendPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("weekend_price >=", value, "weekendPrice");
            return (Criteria) this;
        }

        public Criteria andWeekendPriceLessThan(BigDecimal value) {
            addCriterion("weekend_price <", value, "weekendPrice");
            return (Criteria) this;
        }

        public Criteria andWeekendPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("weekend_price <=", value, "weekendPrice");
            return (Criteria) this;
        }

        public Criteria andWeekendPriceIn(List<BigDecimal> values) {
            addCriterion("weekend_price in", values, "weekendPrice");
            return (Criteria) this;
        }

        public Criteria andWeekendPriceNotIn(List<BigDecimal> values) {
            addCriterion("weekend_price not in", values, "weekendPrice");
            return (Criteria) this;
        }

        public Criteria andWeekendPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("weekend_price between", value1, value2, "weekendPrice");
            return (Criteria) this;
        }

        public Criteria andWeekendPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("weekend_price not between", value1, value2, "weekendPrice");
            return (Criteria) this;
        }

        public Criteria andRoomCountIsNull() {
            addCriterion("room_count is null");
            return (Criteria) this;
        }

        public Criteria andRoomCountIsNotNull() {
            addCriterion("room_count is not null");
            return (Criteria) this;
        }

        public Criteria andRoomCountEqualTo(Integer value) {
            addCriterion("room_count =", value, "roomCount");
            return (Criteria) this;
        }

        public Criteria andRoomCountNotEqualTo(Integer value) {
            addCriterion("room_count <>", value, "roomCount");
            return (Criteria) this;
        }

        public Criteria andRoomCountGreaterThan(Integer value) {
            addCriterion("room_count >", value, "roomCount");
            return (Criteria) this;
        }

        public Criteria andRoomCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("room_count >=", value, "roomCount");
            return (Criteria) this;
        }

        public Criteria andRoomCountLessThan(Integer value) {
            addCriterion("room_count <", value, "roomCount");
            return (Criteria) this;
        }

        public Criteria andRoomCountLessThanOrEqualTo(Integer value) {
            addCriterion("room_count <=", value, "roomCount");
            return (Criteria) this;
        }

        public Criteria andRoomCountIn(List<Integer> values) {
            addCriterion("room_count in", values, "roomCount");
            return (Criteria) this;
        }

        public Criteria andRoomCountNotIn(List<Integer> values) {
            addCriterion("room_count not in", values, "roomCount");
            return (Criteria) this;
        }

        public Criteria andRoomCountBetween(Integer value1, Integer value2) {
            addCriterion("room_count between", value1, value2, "roomCount");
            return (Criteria) this;
        }

        public Criteria andRoomCountNotBetween(Integer value1, Integer value2) {
            addCriterion("room_count not between", value1, value2, "roomCount");
            return (Criteria) this;
        }

        public Criteria andMaxQuantityIsNull() {
            addCriterion("max_quantity is null");
            return (Criteria) this;
        }

        public Criteria andMaxQuantityIsNotNull() {
            addCriterion("max_quantity is not null");
            return (Criteria) this;
        }

        public Criteria andMaxQuantityEqualTo(Integer value) {
            addCriterion("max_quantity =", value, "maxQuantity");
            return (Criteria) this;
        }

        public Criteria andMaxQuantityNotEqualTo(Integer value) {
            addCriterion("max_quantity <>", value, "maxQuantity");
            return (Criteria) this;
        }

        public Criteria andMaxQuantityGreaterThan(Integer value) {
            addCriterion("max_quantity >", value, "maxQuantity");
            return (Criteria) this;
        }

        public Criteria andMaxQuantityGreaterThanOrEqualTo(Integer value) {
            addCriterion("max_quantity >=", value, "maxQuantity");
            return (Criteria) this;
        }

        public Criteria andMaxQuantityLessThan(Integer value) {
            addCriterion("max_quantity <", value, "maxQuantity");
            return (Criteria) this;
        }

        public Criteria andMaxQuantityLessThanOrEqualTo(Integer value) {
            addCriterion("max_quantity <=", value, "maxQuantity");
            return (Criteria) this;
        }

        public Criteria andMaxQuantityIn(List<Integer> values) {
            addCriterion("max_quantity in", values, "maxQuantity");
            return (Criteria) this;
        }

        public Criteria andMaxQuantityNotIn(List<Integer> values) {
            addCriterion("max_quantity not in", values, "maxQuantity");
            return (Criteria) this;
        }

        public Criteria andMaxQuantityBetween(Integer value1, Integer value2) {
            addCriterion("max_quantity between", value1, value2, "maxQuantity");
            return (Criteria) this;
        }

        public Criteria andMaxQuantityNotBetween(Integer value1, Integer value2) {
            addCriterion("max_quantity not between", value1, value2, "maxQuantity");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table room_type_info
     *
   * @mbg.generated do_not_delete_during_merge Thu Nov 28 17:12:09 GMT 2024
     */
    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table room_type_info
     *
   * @mbg.generated Thu Nov 28 17:12:09 GMT 2024
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}