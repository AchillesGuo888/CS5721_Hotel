package com.example.hotel.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.hotel.dto.PostQueryCondition;
import com.example.hotel.entity.Order;
import com.example.hotel.entity.Post;
import org.apache.ibatis.annotations.*;

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
    @Select("SELECT * FROM t_order WHERE order_id = #{orderId}")
    static Order findOrderById(@Param("orderId") Long orderId) {
        return null;
    }

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

    /**
     * Update order information based on order ID
     * @param order order, containing the updated fields
     * @return Number of records updated
     */
    @Update("UPDATE t_order SET status = #{status}, pending_cancel_rooms = #{pendingCancelRooms} WHERE order_id = #{orderId}")
    int updateOrder(Order order);

    /**
     * Update order status
     * @param orderId
     * @param status New order status
     * @return Number of rows affected
     */
    @Update("UPDATE t_order SET status = #{status} WHERE order_id = #{orderId}")
    int updateOrderStatus(@Param("orderId") Long orderId, @Param("status") String status);

    @Insert("INSERT INTO t_order (user_id, post_id, quantity, name, phone, id_card, start_date, order_id, status, price, total_price, post_title, post_number, earned_points) " +
            "VALUES (#{userId}, #{postId}, #{quantity}, #{name}, #{phone}, #{idCard}, #{startDate}, #{orderId}, #{status}, #{price}, #{totalPrice}, #{postTitle}, #{postNumber}, #{earnedPoints})")
    @Options(useGeneratedKeys = true, keyProperty = "orderId")
    int saveOrder(Order order);

    @Select("SELECT * FROM t_order")
    List<Order> findAllOrders();

    @Update("UPDATE order_detail " +
            "SET pending_cancel_rooms = #{pendingCancelRoomsStr}, is_deleted = 1 " +
            "WHERE order_id = #{orderId} AND room_number IN (${roomNumbers})")
    void updatePendingCancelRooms(@Param("orderId") int orderId,
                                  @Param("pendingCancelRoomsStr") String pendingCancelRoomsStr,
                                  @Param("roomNumbers") String roomNumbers);

    @Select("SELECT order_id, pending_cancel_rooms FROM orders WHERE order_id = #{orderId}")
    @ResultMap("orderResultMap")
    Order selectOrder(int orderId);
}

