package com.example.hotel.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PointInfoExample {

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database table
   * point_info
   *
   * @mbg.generated Sun Nov 10 00:55:30 GMT 2024
   */
  protected String orderByClause;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database table
   * point_info
   *
   * @mbg.generated Sun Nov 10 00:55:30 GMT 2024
   */
  protected boolean distinct;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database table
   * point_info
   *
   * @mbg.generated Sun Nov 10 00:55:30 GMT 2024
   */
  protected List<Criteria> oredCriteria;

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * point_info
   *
   * @mbg.generated Sun Nov 10 00:55:30 GMT 2024
   */
  public PointInfoExample() {
    oredCriteria = new ArrayList<>();
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * point_info
   *
   * @mbg.generated Sun Nov 10 00:55:30 GMT 2024
   */
  public void setOrderByClause(String orderByClause) {
    this.orderByClause = orderByClause;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * point_info
   *
   * @mbg.generated Sun Nov 10 00:55:30 GMT 2024
   */
  public String getOrderByClause() {
    return orderByClause;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * point_info
   *
   * @mbg.generated Sun Nov 10 00:55:30 GMT 2024
   */
  public void setDistinct(boolean distinct) {
    this.distinct = distinct;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * point_info
   *
   * @mbg.generated Sun Nov 10 00:55:30 GMT 2024
   */
  public boolean isDistinct() {
    return distinct;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * point_info
   *
   * @mbg.generated Sun Nov 10 00:55:30 GMT 2024
   */
  public List<Criteria> getOredCriteria() {
    return oredCriteria;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * point_info
   *
   * @mbg.generated Sun Nov 10 00:55:30 GMT 2024
   */
  public void or(Criteria criteria) {
    oredCriteria.add(criteria);
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * point_info
   *
   * @mbg.generated Sun Nov 10 00:55:30 GMT 2024
   */
  public Criteria or() {
    Criteria criteria = createCriteriaInternal();
    oredCriteria.add(criteria);
    return criteria;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * point_info
   *
   * @mbg.generated Sun Nov 10 00:55:30 GMT 2024
   */
  public Criteria createCriteria() {
    Criteria criteria = createCriteriaInternal();
    if (oredCriteria.size() == 0) {
      oredCriteria.add(criteria);
    }
    return criteria;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * point_info
   *
   * @mbg.generated Sun Nov 10 00:55:30 GMT 2024
   */
  protected Criteria createCriteriaInternal() {
    Criteria criteria = new Criteria();
    return criteria;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * point_info
   *
   * @mbg.generated Sun Nov 10 00:55:30 GMT 2024
   */
  public void clear() {
    oredCriteria.clear();
    orderByClause = null;
    distinct = false;
  }

  /**
   * This class was generated by MyBatis Generator. This class corresponds to the database table
   * point_info
   *
   * @mbg.generated Sun Nov 10 00:55:30 GMT 2024
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

    public Criteria andOrderIdIsNull() {
      addCriterion("order_id is null");
      return (Criteria) this;
    }

    public Criteria andOrderIdIsNotNull() {
      addCriterion("order_id is not null");
      return (Criteria) this;
    }

    public Criteria andOrderIdEqualTo(Long value) {
      addCriterion("order_id =", value, "orderId");
      return (Criteria) this;
    }

    public Criteria andOrderIdNotEqualTo(Long value) {
      addCriterion("order_id <>", value, "orderId");
      return (Criteria) this;
    }

    public Criteria andOrderIdGreaterThan(Long value) {
      addCriterion("order_id >", value, "orderId");
      return (Criteria) this;
    }

    public Criteria andOrderIdGreaterThanOrEqualTo(Long value) {
      addCriterion("order_id >=", value, "orderId");
      return (Criteria) this;
    }

    public Criteria andOrderIdLessThan(Long value) {
      addCriterion("order_id <", value, "orderId");
      return (Criteria) this;
    }

    public Criteria andOrderIdLessThanOrEqualTo(Long value) {
      addCriterion("order_id <=", value, "orderId");
      return (Criteria) this;
    }

    public Criteria andOrderIdIn(List<Long> values) {
      addCriterion("order_id in", values, "orderId");
      return (Criteria) this;
    }

    public Criteria andOrderIdNotIn(List<Long> values) {
      addCriterion("order_id not in", values, "orderId");
      return (Criteria) this;
    }

    public Criteria andOrderIdBetween(Long value1, Long value2) {
      addCriterion("order_id between", value1, value2, "orderId");
      return (Criteria) this;
    }

    public Criteria andOrderIdNotBetween(Long value1, Long value2) {
      addCriterion("order_id not between", value1, value2, "orderId");
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

    public Criteria andPointCountIsNull() {
      addCriterion("point_count is null");
      return (Criteria) this;
    }

    public Criteria andPointCountIsNotNull() {
      addCriterion("point_count is not null");
      return (Criteria) this;
    }

    public Criteria andPointCountEqualTo(Integer value) {
      addCriterion("point_count =", value, "pointCount");
      return (Criteria) this;
    }

    public Criteria andPointCountNotEqualTo(Integer value) {
      addCriterion("point_count <>", value, "pointCount");
      return (Criteria) this;
    }

    public Criteria andPointCountGreaterThan(Integer value) {
      addCriterion("point_count >", value, "pointCount");
      return (Criteria) this;
    }

    public Criteria andPointCountGreaterThanOrEqualTo(Integer value) {
      addCriterion("point_count >=", value, "pointCount");
      return (Criteria) this;
    }

    public Criteria andPointCountLessThan(Integer value) {
      addCriterion("point_count <", value, "pointCount");
      return (Criteria) this;
    }

    public Criteria andPointCountLessThanOrEqualTo(Integer value) {
      addCriterion("point_count <=", value, "pointCount");
      return (Criteria) this;
    }

    public Criteria andPointCountIn(List<Integer> values) {
      addCriterion("point_count in", values, "pointCount");
      return (Criteria) this;
    }

    public Criteria andPointCountNotIn(List<Integer> values) {
      addCriterion("point_count not in", values, "pointCount");
      return (Criteria) this;
    }

    public Criteria andPointCountBetween(Integer value1, Integer value2) {
      addCriterion("point_count between", value1, value2, "pointCount");
      return (Criteria) this;
    }

    public Criteria andPointCountNotBetween(Integer value1, Integer value2) {
      addCriterion("point_count not between", value1, value2, "pointCount");
      return (Criteria) this;
    }

    public Criteria andUserIdIsNull() {
      addCriterion("user_id is null");
      return (Criteria) this;
    }

    public Criteria andUserIdIsNotNull() {
      addCriterion("user_id is not null");
      return (Criteria) this;
    }

    public Criteria andUserIdEqualTo(String value) {
      addCriterion("user_id =", value, "userId");
      return (Criteria) this;
    }

    public Criteria andUserIdNotEqualTo(String value) {
      addCriterion("user_id <>", value, "userId");
      return (Criteria) this;
    }

    public Criteria andUserIdGreaterThan(String value) {
      addCriterion("user_id >", value, "userId");
      return (Criteria) this;
    }

    public Criteria andUserIdGreaterThanOrEqualTo(String value) {
      addCriterion("user_id >=", value, "userId");
      return (Criteria) this;
    }

    public Criteria andUserIdLessThan(String value) {
      addCriterion("user_id <", value, "userId");
      return (Criteria) this;
    }

    public Criteria andUserIdLessThanOrEqualTo(String value) {
      addCriterion("user_id <=", value, "userId");
      return (Criteria) this;
    }

    public Criteria andUserIdLike(String value) {
      addCriterion("user_id like", value, "userId");
      return (Criteria) this;
    }

    public Criteria andUserIdNotLike(String value) {
      addCriterion("user_id not like", value, "userId");
      return (Criteria) this;
    }

    public Criteria andUserIdIn(List<String> values) {
      addCriterion("user_id in", values, "userId");
      return (Criteria) this;
    }

    public Criteria andUserIdNotIn(List<String> values) {
      addCriterion("user_id not in", values, "userId");
      return (Criteria) this;
    }

    public Criteria andUserIdBetween(String value1, String value2) {
      addCriterion("user_id between", value1, value2, "userId");
      return (Criteria) this;
    }

    public Criteria andUserIdNotBetween(String value1, String value2) {
      addCriterion("user_id not between", value1, value2, "userId");
      return (Criteria) this;
    }

    public Criteria andReasonTypeIsNull() {
      addCriterion("reason_type is null");
      return (Criteria) this;
    }

    public Criteria andReasonTypeIsNotNull() {
      addCriterion("reason_type is not null");
      return (Criteria) this;
    }

    public Criteria andReasonTypeEqualTo(Byte value) {
      addCriterion("reason_type =", value, "reasonType");
      return (Criteria) this;
    }

    public Criteria andReasonTypeNotEqualTo(Byte value) {
      addCriterion("reason_type <>", value, "reasonType");
      return (Criteria) this;
    }

    public Criteria andReasonTypeGreaterThan(Byte value) {
      addCriterion("reason_type >", value, "reasonType");
      return (Criteria) this;
    }

    public Criteria andReasonTypeGreaterThanOrEqualTo(Byte value) {
      addCriterion("reason_type >=", value, "reasonType");
      return (Criteria) this;
    }

    public Criteria andReasonTypeLessThan(Byte value) {
      addCriterion("reason_type <", value, "reasonType");
      return (Criteria) this;
    }

    public Criteria andReasonTypeLessThanOrEqualTo(Byte value) {
      addCriterion("reason_type <=", value, "reasonType");
      return (Criteria) this;
    }

    public Criteria andReasonTypeIn(List<Byte> values) {
      addCriterion("reason_type in", values, "reasonType");
      return (Criteria) this;
    }

    public Criteria andReasonTypeNotIn(List<Byte> values) {
      addCriterion("reason_type not in", values, "reasonType");
      return (Criteria) this;
    }

    public Criteria andReasonTypeBetween(Byte value1, Byte value2) {
      addCriterion("reason_type between", value1, value2, "reasonType");
      return (Criteria) this;
    }

    public Criteria andReasonTypeNotBetween(Byte value1, Byte value2) {
      addCriterion("reason_type not between", value1, value2, "reasonType");
      return (Criteria) this;
    }
  }

  /**
   * This class was generated by MyBatis Generator. This class corresponds to the database table
   * point_info
   *
   * @mbg.generated do_not_delete_during_merge Sun Nov 10 00:55:30 GMT 2024
   */
  public static class Criteria extends GeneratedCriteria {

    protected Criteria() {
      super();
    }
  }

  /**
   * This class was generated by MyBatis Generator. This class corresponds to the database table
   * point_info
   *
   * @mbg.generated Sun Nov 10 00:55:30 GMT 2024
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