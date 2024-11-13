package com.example.hotel.mapper.ext;

import com.example.hotel.dto.AvailableRoomCountDTO;
import com.example.hotel.dto.DistributableRoomDTO;
import java.time.LocalDate;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

public interface OrderAndDetailMapperExt {
    @Select({
        "select  "
            + "  rti.id roomTypeId,  "
            + "  rti.hotel_id hotelId,   "
            + "  rti.type_name roomTypeName,   "
            + "  COUNT(distinct (od.room_number)) as bookedRoomsCount,  "
            + "  rti.room_count - COUNT(distinct (od.room_number)) available  "
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
            + "    ob.start_date <= #{endDate}  "
            + "    and ob.end_date >= #{startDate})  "
            + "  or (od.status = 2  "
            + "    and   "
            + "    ob.start_date <= #{endDate}  "
            + "    and od.update_time >= #{startDate})  "
            + "where   "
            + " rti.is_deleted=0 "
            + "  <if test='hotelIds != null and hotelIds.size()>0'>"
            + "     <foreach collection='hotelIds' item='hotelId' open=' and rti.hotel_id in(' close=') ' separator=','>"
            + "         #{hotelId}"
            + "     </foreach>"
            + "  </if>                                                      "
            + "  <if test='quantity != null and quantity>0'>"
            + "     and rti.max_quantity >= #{quantity} "
            + "  </if>                                                      "
            + "  <if test='roomTypeIds != null and roomTypeIds.size()>0'>"
            + "     <foreach collection='roomTypeIds' item='roomTypeId' open=' and rti.room_type_id in(' close=') ' separator=','>"
            + "         #{roomTypeId}"
            + "     </foreach>"
            + "  </if>                                                      "
            + "group by  "
            + "  rti.id ,  "
            + "  rti.hotel_id ;"
        }
    )
    @ResultType(AvailableRoomCountDTO.class)
    List<AvailableRoomCountDTO> getAvailableRoomType(@Param("hotelIds") List<Long> hotelIds,@Param("startDate") LocalDate startDate,@Param("endDate") LocalDate endDate,@Param("quantity")Integer quantity,@Param("roomTypeIds") List<Long> roomTypeIds);

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
    List<DistributableRoomDTO> getDistributableRoomList(@Param("roomTypeId") Long roomTypeId,@Param("hotelId") Long hotelId,@Param("startDate") LocalDate startDate,@Param("endDate") LocalDate endDate);

}