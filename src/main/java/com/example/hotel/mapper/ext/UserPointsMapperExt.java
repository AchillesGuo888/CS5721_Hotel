package com.example.hotel.mapper.ext;

import com.example.hotel.entity.UserPoints;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

public interface UserPointsMapperExt {

  @Select({"<script>"
      +"select "
          + " user_id userId, "
          + " sum(points) points "
          + "from "
          + " user_points up "
          + "where "
          + " up.is_deleted = 0 "
          + "  <if test='userIds != null and userIds.size()>0'>"
          + "     <foreach collection='userIds' item='userId' open=' and up.user_id in(' close=')"
          + " ' separator=','>"
          + "         #{userId}"
          + "     </foreach>"
          + "  </if>                                                      "
          + "group by "
          + " user_id;"
          +"</script>"
  }
  )
  @ResultType(UserPoints.class)
  List<UserPoints> getUserPointSum(@Param("userIds") List<String> userIds);

}