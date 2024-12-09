package com.example.hotel.mapper;

import com.example.hotel.entity.OrderDetail;
import com.example.hotel.entity.OrderDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface OrderDetailMapper {

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * order_detail
   *
   * @mbg.generated Fri Nov 29 00:34:27 GMT 2024
   */
  long countByExample(OrderDetailExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * order_detail
   *
   * @mbg.generated Fri Nov 29 00:34:27 GMT 2024
   */
  int deleteByExample(OrderDetailExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * order_detail
   *
   * @mbg.generated Fri Nov 29 00:34:27 GMT 2024
   */
  int deleteByPrimaryKey(Long id);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * order_detail
   *
   * @mbg.generated Fri Nov 29 00:34:27 GMT 2024
   */
  int insert(OrderDetail row);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * order_detail
   *
   * @mbg.generated Fri Nov 29 00:34:27 GMT 2024
   */
  int insertSelective(OrderDetail row);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * order_detail
   *
   * @mbg.generated Fri Nov 29 00:34:27 GMT 2024
   */
  List<OrderDetail> selectByExample(OrderDetailExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * order_detail
   *
   * @mbg.generated Fri Nov 29 00:34:27 GMT 2024
   */
  OrderDetail selectByPrimaryKey(Long id);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * order_detail
   *
   * @mbg.generated Fri Nov 29 00:34:27 GMT 2024
   */
  int updateByExampleSelective(@Param("row") OrderDetail row,
      @Param("example") OrderDetailExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * order_detail
   *
   * @mbg.generated Fri Nov 29 00:34:27 GMT 2024
   */
  int updateByExample(@Param("row") OrderDetail row,
      @Param("example") OrderDetailExample example);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * order_detail
   *
   * @mbg.generated Fri Nov 29 00:34:27 GMT 2024
   */
  int updateByPrimaryKeySelective(OrderDetail row);

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table
   * order_detail
   *
   * @mbg.generated Fri Nov 29 00:34:27 GMT 2024
   */
  int updateByPrimaryKey(OrderDetail row);

  // Find order details by order ID
  @Select("SELECT * FROM order_detail WHERE order_id = #{orderId} AND is_deleted = 0")
  OrderDetail findOrderById(Long orderId);

  @Select("SELECT * FROM order_detail WHERE order_id = #{orderId} AND room_number = #{roomNumber} AND is_deleted = 0")
  OrderDetail findOrderByOrderIdAndRoom(@Param("orderId") Long orderId, @Param("roomNumber") Long roomNumber);

  // Update order status to Cancelled
  @Update("UPDATE order_detail SET status = 1, update_time = NOW() WHERE order_id = #{orderId}")
  int updateOrderStatusToCancelled(Long orderId);

  /**
   * Update the status of rooms by order ID.
   *
   * @param orderId The ID of the order.
   * @param newStatus The new status to set.
   * @return Number of rows affected.
   */
  @Update("UPDATE order_detail SET status = #{newStatus}, update_time = NOW() WHERE order_id = #{orderId} AND is_deleted = 0")
  int updateRoomStatusByOrderId(@Param("orderId") Long orderId, @Param("newStatus") int newStatus);

  /**
   * Count active rooms by order ID.
   *
   * @param orderId The ID of the order.
   * @return The number of active rooms.
   */
  @Select("SELECT COUNT(*) num FROM order_detail WHERE order_id = #{orderId} AND status = 0 AND is_deleted = 0")
  int countActiveRoomsByOrderId(@Param("orderId") Long orderId);

  @Update({
          "UPDATE order_detail",
          "SET status = #{newStatus}, update_time = NOW()",
          "WHERE room_number = #{roomNumber}",
          "AND order_id = #{orderId} AND is_deleted = 0"
  })
  int updateRoomStatusByRoomNumber(@Param("orderId") Long orderId,
                                   @Param("roomNumber") Long roomNumber,
                                   @Param("newStatus") int newStatus);


  void deleteById(Long orderId);
}