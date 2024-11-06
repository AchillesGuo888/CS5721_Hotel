package com.example.hotel.mapper.ext;

import com.example.hotel.dto.AvailableRoomCountDTO;
import java.time.LocalDate;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

public interface OrderAndDetailMapperExt {
    @Select({
        "select "
            + " od.room_type_id roomTypeId, "
            + " ob.hotel_id hotelId, "
            + " COUNT(distinct (od.room_number)) as bookedRoomsCount, "
            + " rti.room_count - COUNT(distinct (od.room_number)) availableCount "
            + "from "
            + " order_detail od "
            + "join  "
            + "    order_base ob on "
            + " od.order_id = ob.id "
            + "join room_type_info rti on "
            + " od.room_type_id = rti.id "
            + "where "
            + " ob.is_deleted=0 "
            + "  <if test='hotelIds != null and hotelIds.size()>0'>"
            + "     <foreach collection='hotelIds' item='hotelId' open=' and ob.hotel_id in(' close=') ' separator=','>"
            + "         #{hotelId}"
            + "     </foreach>"
            + "  </if>                                                      "
            + " and rti.max_quantity >= #{quantity} "
            + " and  "
            + "  ((od.status = 0 "
            + "  and  "
            + "    ob.start_date <= #{endDate} "
            + "  and ob.end_date >= #{startDate}) "
            + " or (od.status = 2 "
            + "  and  "
            + "    ob.start_date <= #{endDate} "
            + "  and od.update_time >= #{startDate})) "
            + "group by "
            + " od.room_type_id, "
            + " ob.hotel_id ;"
        }
    )
    @ResultType(AvailableRoomCountDTO.class)
    List<AvailableRoomCountDTO> getAvailableRoomType(@Param("hotelIds") List<Long> hotelIds,@Param("startDate") LocalDate startDate,@Param("endDate") LocalDate endDate,@Param("quantity")Integer quantity);

}