package com.example.hotel.mapper;

import com.example.hotel.entity.PointInfo;
import com.example.hotel.entity.PointInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PointInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table point_info
     *
     * @mbg.generated Sun Nov 10 00:55:30 GMT 2024
     */
    long countByExample(PointInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table point_info
     *
     * @mbg.generated Sun Nov 10 00:55:30 GMT 2024
     */
    int deleteByExample(PointInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table point_info
     *
     * @mbg.generated Sun Nov 10 00:55:30 GMT 2024
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table point_info
     *
     * @mbg.generated Sun Nov 10 00:55:30 GMT 2024
     */
    int insert(PointInfo row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table point_info
     *
     * @mbg.generated Sun Nov 10 00:55:30 GMT 2024
     */
    int insertSelective(PointInfo row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table point_info
     *
     * @mbg.generated Sun Nov 10 00:55:30 GMT 2024
     */
    List<PointInfo> selectByExample(PointInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table point_info
     *
     * @mbg.generated Sun Nov 10 00:55:30 GMT 2024
     */
    PointInfo selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table point_info
     *
     * @mbg.generated Sun Nov 10 00:55:30 GMT 2024
     */
    int updateByExampleSelective(@Param("row") PointInfo row, @Param("example") PointInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table point_info
     *
     * @mbg.generated Sun Nov 10 00:55:30 GMT 2024
     */
    int updateByExample(@Param("row") PointInfo row, @Param("example") PointInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table point_info
     *
     * @mbg.generated Sun Nov 10 00:55:30 GMT 2024
     */
    int updateByPrimaryKeySelective(PointInfo row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table point_info
     *
     * @mbg.generated Sun Nov 10 00:55:30 GMT 2024
     */
    int updateByPrimaryKey(PointInfo row);
}