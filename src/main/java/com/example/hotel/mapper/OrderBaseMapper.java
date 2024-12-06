package com.example.hotel.mapper;

import com.example.hotel.entity.OrderBase;
import com.example.hotel.entity.OrderBaseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface OrderBaseMapper {

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * order_base
   *
   * @mbg.generated Thu Nov 28 17:11:21 GMT 2024
   */
  long countByExample(OrderBaseExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * order_base
   *
   * @mbg.generated Thu Nov 28 17:11:21 GMT 2024
   */
  int deleteByExample(OrderBaseExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * order_base
   *
   * @mbg.generated Thu Nov 28 17:11:21 GMT 2024
   */
  int deleteByPrimaryKey(Long id);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * order_base
   *
   * @mbg.generated Thu Nov 28 17:11:21 GMT 2024
   */
  int insert(OrderBase row);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * order_base
   *
   * @mbg.generated Thu Nov 28 17:11:21 GMT 2024
   */
  int insertSelective(OrderBase row);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * order_base
   *
   * @mbg.generated Thu Nov 28 17:11:21 GMT 2024
   */
  List<OrderBase> selectByExample(OrderBaseExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * order_base
   *
   * @mbg.generated Thu Nov 28 17:11:21 GMT 2024
   */
  OrderBase selectByPrimaryKey(Long id);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * order_base
   *
   * @mbg.generated Thu Nov 28 17:11:21 GMT 2024
   */
  int updateByExampleSelective(@Param("row") OrderBase row,
      @Param("example") OrderBaseExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * order_base
   *
   * @mbg.generated Thu Nov 28 17:11:21 GMT 2024
   */
  int updateByExample(@Param("row") OrderBase row, @Param("example") OrderBaseExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * order_base
   *
   * @mbg.generated Thu Nov 28 17:11:21 GMT 2024
   */
  int updateByPrimaryKeySelective(OrderBase row);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * order_base
   *
   * @mbg.generated Thu Nov 28 17:11:21 GMT 2024
   */
  int updateByPrimaryKey(OrderBase row);

  /**
   * Update order status to cancelled
   *
   * @param orderId The ID of the order to cancel
   * @return Number of rows affected
   */
  @Update("UPDATE order_base SET status = 1, is_cancelled = 1, update_time = NOW() WHERE id = #{orderId} AND is_cancelled = 0")
  int updateOrderStatusToCancelled(@Param("orderId") Long orderId);
}