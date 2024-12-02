package com.example.hotel.mapper;

import com.example.hotel.entity.OrderDetail;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderDetailMapper {

    @Select("SELECT * FROM order_detail WHERE order_id = #{orderId} AND is_deleted = 0")
    List<OrderDetail> getOrderDetailsByOrderId(Long orderId);

    @Update("UPDATE order_detail SET status = #{status} WHERE id = #{id}")
    void updateOrderDetailStatus(@Param("id") Long id, @Param("status") Integer status);
}
