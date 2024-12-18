package com.example.hotel.mapper;

import com.example.hotel.entity.RoomTypePrice;
import com.example.hotel.entity.RoomTypePriceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoomTypePriceMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_type_price
     *
     * @mbg.generated Thu Nov 28 23:25:50 IST 2024
     */
    long countByExample(RoomTypePriceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_type_price
     *
     * @mbg.generated Thu Nov 28 23:25:50 IST 2024
     */
    int deleteByExample(RoomTypePriceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_type_price
     *
     * @mbg.generated Thu Nov 28 23:25:50 IST 2024
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_type_price
     *
     * @mbg.generated Thu Nov 28 23:25:50 IST 2024
     */
    int insert(RoomTypePrice row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_type_price
     *
     * @mbg.generated Thu Nov 28 23:25:50 IST 2024
     */
    int insertSelective(RoomTypePrice row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_type_price
     *
     * @mbg.generated Thu Nov 28 23:25:50 IST 2024
     */
    List<RoomTypePrice> selectByExample(RoomTypePriceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_type_price
     *
     * @mbg.generated Thu Nov 28 23:25:50 IST 2024
     */
    RoomTypePrice selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_type_price
     *
     * @mbg.generated Thu Nov 28 23:25:50 IST 2024
     */
    int updateByExampleSelective(@Param("row") RoomTypePrice row, @Param("example") RoomTypePriceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_type_price
     *
     * @mbg.generated Thu Nov 28 23:25:50 IST 2024
     */
    int updateByExample(@Param("row") RoomTypePrice row, @Param("example") RoomTypePriceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_type_price
     *
     * @mbg.generated Thu Nov 28 23:25:50 IST 2024
     */
    int updateByPrimaryKeySelective(RoomTypePrice row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_type_price
     *
     * @mbg.generated Thu Nov 28 23:25:50 IST 2024
     */
    int updateByPrimaryKey(RoomTypePrice row);
}