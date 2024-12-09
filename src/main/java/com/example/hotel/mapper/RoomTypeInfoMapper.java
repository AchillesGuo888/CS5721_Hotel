package com.example.hotel.mapper;

import com.example.hotel.entity.RoomTypeInfo;
import com.example.hotel.entity.RoomTypeInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoomTypeInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_type_info
     *
     * @mbg.generated Mon Dec 09 13:24:48 GMT 2024
     */
    long countByExample(RoomTypeInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_type_info
     *
     * @mbg.generated Mon Dec 09 13:24:48 GMT 2024
     */
    int deleteByExample(RoomTypeInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_type_info
     *
     * @mbg.generated Mon Dec 09 13:24:48 GMT 2024
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_type_info
     *
     * @mbg.generated Mon Dec 09 13:24:48 GMT 2024
     */
    int insert(RoomTypeInfo row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_type_info
     *
     * @mbg.generated Mon Dec 09 13:24:48 GMT 2024
     */
    int insertSelective(RoomTypeInfo row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_type_info
     *
     * @mbg.generated Mon Dec 09 13:24:48 GMT 2024
     */
    List<RoomTypeInfo> selectByExampleWithBLOBs(RoomTypeInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_type_info
     *
     * @mbg.generated Mon Dec 09 13:24:48 GMT 2024
     */
    List<RoomTypeInfo> selectByExample(RoomTypeInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_type_info
     *
     * @mbg.generated Mon Dec 09 13:24:48 GMT 2024
     */
    RoomTypeInfo selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_type_info
     *
     * @mbg.generated Mon Dec 09 13:24:48 GMT 2024
     */
    int updateByExampleSelective(@Param("row") RoomTypeInfo row, @Param("example") RoomTypeInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_type_info
     *
     * @mbg.generated Mon Dec 09 13:24:48 GMT 2024
     */
    int updateByExampleWithBLOBs(@Param("row") RoomTypeInfo row, @Param("example") RoomTypeInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_type_info
     *
     * @mbg.generated Mon Dec 09 13:24:48 GMT 2024
     */
    int updateByExample(@Param("row") RoomTypeInfo row, @Param("example") RoomTypeInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_type_info
     *
     * @mbg.generated Mon Dec 09 13:24:48 GMT 2024
     */
    int updateByPrimaryKeySelective(RoomTypeInfo row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_type_info
     *
     * @mbg.generated Mon Dec 09 13:24:48 GMT 2024
     */
    int updateByPrimaryKeyWithBLOBs(RoomTypeInfo row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table room_type_info
     *
     * @mbg.generated Mon Dec 09 13:24:48 GMT 2024
     */
    int updateByPrimaryKey(RoomTypeInfo row);
}