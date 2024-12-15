package com.example.hotel.mapper.ext;


import com.example.hotel.entity.UserPoints;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;


public interface BatchInsertMapperExt {

  @Insert({
      "<script>"
      +"INSERT INTO user_points (user_id, points, order_id, expense) VALUES"
      +"<foreach collection='userPointsList' item='pointInfo' separator=','>"
      +"(#{pointInfo.userId}, #{pointInfo.points}, #{pointInfo.orderId}, #{pointInfo.expense})"
      +"</foreach>"
      +"</script>"
  })
  void batchInsertUserPoints(@Param("userPointsList") List<UserPoints> userPointsList);
}