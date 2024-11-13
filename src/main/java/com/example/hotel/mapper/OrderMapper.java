package com.example.hotel.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotel.dto.PostQueryCondition;
import com.example.hotel.entity.Order;
import com.example.hotel.entity.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author liuyanzhao
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    /**
     * Query orders by time range
     *
     * @param startDate
     * @param endDate
     * @return
     */
    List<Order> findAll(@Param("startDate") String startDate,
                        @Param("endDate") String endDate,
                        Page page);

    /**
     * Find an order by order ID
     */
    @Select("SELECT * FROM orders WHERE id = #{orderId}")
    Order findOrderById(@Param("orderId")Long orderId);

    /**
     * Query the total amount by time range
     *
     * @param startDate
     * @param endDate
     * @return
     */
    Integer getTotalPriceSum(@Param("startDate") String startDate,
                             @Param("endDate") String endDate);

    /**
     * Cancel order
     *
     * @param orderId
     * @param status
     * @return
     */
    int cancelOrder(@Param("orderId") Long orderId,
                    @Param("status") Integer status);

    /**
     * Find the number of bookings associated with a given hotel
     *
     * @param hotelId
     * @return number of orders
     */
    int findOrdersByHotelId(@Param("hotelId") Long hotelId);
}

