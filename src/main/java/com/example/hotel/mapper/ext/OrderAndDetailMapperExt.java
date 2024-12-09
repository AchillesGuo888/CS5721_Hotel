package com.example.hotel.mapper.ext;

import com.example.hotel.dto.AvailableRoomCountDTO;
import com.example.hotel.dto.DistributableRoomDTO;
import com.example.hotel.dto.request.QueryAvailableParam;
import com.example.hotel.dto.response.OrderInfoListResponse;
import java.time.LocalDate;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface OrderAndDetailMapperExt {

  @Select({"<script>"
          + " select  "
          + "  rti.id roomTypeId,  "
          + "  rti.hotel_id hotelId,   "
          + "  rti.type_name roomTypeName,   "
          + "  COUNT(distinct (od.room_number)) as bookedRoomsCount,  "
          + "  rti.room_count - COUNT(distinct (od.room_number)) availableCount  "
          + "from  "
          + "  room_type_info rti  "
          + "left join order_detail od on  "
          + "  od.room_type_id = rti.id  "
          + "left join   "
          + "    order_base ob on  "
          + "  od.order_id = ob.id  "
          + "  and ob.is_cancelled = 0  "
          + "  and   "
          + "   ((od.status = 0  "
          + "    and   "
          + "    ob.start_date &lt;= #{params.endDate}  "
          + "    and ob.end_date >= #{params.startDate})  "
          + "  or (od.status = 2  "
          + "    and   "
          + "    ob.start_date &lt;= #{params.endDate}  "
          + "    and od.update_time >= #{params.startDate}))  "
          + "where   "
          + " rti.is_deleted=0 "
          + "  <if test='params.hotelIds != null and params.hotelIds.size()>0'>"
          + "     <foreach collection='params.hotelIds' item='hotelId' open=' and rti.hotel_id in(' "
          + "close=') ' separator=','>"
          + "         #{hotelId}"
          + "     </foreach>"
          + "  </if>                                                      "
          + "  <if test='params.quantity != null and params.quantity>0'>"
          + "     and rti.max_quantity >= #{params.quantity} "
          + "  </if>                                                      "
          + "  <if test='params.roomTypeIds != null and params.roomTypeIds.size()>0'>"
          + "     <foreach collection='params.roomTypeIds' item='roomTypeId' open=' and rti.id"
          + " in(' close=') ' separator=','>"
          + "         #{roomTypeId}"
          + "     </foreach>"
          + "  </if>                                                      "
          + "group by  "
          + "  rti.id ,  "
          + "  rti.hotel_id "
      +"</script>"
  })
  @ResultType(AvailableRoomCountDTO.class)
  List<AvailableRoomCountDTO> getAvailableRoomType(@Param("params") QueryAvailableParam params);

  @Select({
      "select "
          + " ri.room_number roomNumber, "
          + " ri.id "
          + "from "
          + " room_info ri "
          + "where "
          + " ri.room_type_id = #{roomTypeId} "
          + " and ri.hotel_id = #{hotelId} "
          + " and ri.room_number not in ( "
          + " select "
          + "  od.room_number "
          + " from "
          + "  order_detail od "
          + " join order_base ob on "
          + "  od.order_id = ob.id "
          + " where "
          + "  od.is_deleted = 0 "
          + "  and  "
          + "    (( od.change_type = 0 "
          + "   and od.status = 0 "
          + "   and ob.start_date < #{endDate} "
          + "   and ob.end_date > #{startDate}) "
          + "  or(od.status = 2 "
          + "   and ob.start_date < #{endDate} "
          + "   and od.update_time > #{startDate})) "
          + "  ); "
  }
  )
  @ResultType(DistributableRoomDTO.class)
  List<DistributableRoomDTO> getDistributableRoomList(@Param("roomTypeId") Long roomTypeId,
      @Param("hotelId") Long hotelId, @Param("startDate") LocalDate startDate,
      @Param("endDate") LocalDate endDate);

  @Select({"<script>"
      +"select "
          + " ob.id orderId, "
          + " ob.hotel_id hotelId, "
          + " hi.hotel_name hotelName, "
          + " ob.start_date startDate, "
          + " ob.end_date endDate, "
          + " ob.real_price realPrice, "
          + " rti.type_name typeName "
          + "from "
          + " order_base ob "
          + "left join hotel_info hi on "
          + " ob.hotel_id = hi.id "
          + "left join order_detail od on "
          + " ob.id = od.order_id "
          + " and od.status &lt;> 1 "
          + "left join room_type_info rti on "
          + " od.room_type_id = rti.id "
          + " and ob.hotel_id = rti.hotel_id "
          + "where "
          + " ob.is_deleted = 0 "
          + "  <if test='orderIds != null and orderIds.size()>0'>"
          + "     <foreach collection='orderIds' item='orderId' open=' and ob.id in (' close=') '"
          + " separator=','>"
          + "         #{orderId}"
          + "     </foreach>"
          + "  </if>                                                      "
          + " and ob.user_id = #{userId}"
      +"</script>"
  }
  )
  @ResultType(OrderInfoListResponse.class)
  List<OrderInfoListResponse> getOrderListInfo(@Param("userId") String userId,
      @Param("orderIds") List<Long> orderIds);




}