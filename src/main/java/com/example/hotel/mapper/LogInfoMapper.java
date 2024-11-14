package com.example.hotel.mapper;

import com.example.hotel.entity.LogInfo;
import com.example.hotel.entity.LogInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LogInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_info
     *
     * @mbg.generated Wed Nov 13 23:35:15 GMT 2024
     */
    long countByExample(LogInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_info
     *
     * @mbg.generated Wed Nov 13 23:35:15 GMT 2024
     */
    int deleteByExample(LogInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_info
     *
     * @mbg.generated Wed Nov 13 23:35:15 GMT 2024
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_info
     *
     * @mbg.generated Wed Nov 13 23:35:15 GMT 2024
     */
    int insert(LogInfo row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_info
     *
     * @mbg.generated Wed Nov 13 23:35:15 GMT 2024
     */
    int insertSelective(LogInfo row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_info
     *
     * @mbg.generated Wed Nov 13 23:35:15 GMT 2024
     */
    List<LogInfo> selectByExample(LogInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_info
     *
     * @mbg.generated Wed Nov 13 23:35:15 GMT 2024
     */
    LogInfo selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_info
     *
     * @mbg.generated Wed Nov 13 23:35:15 GMT 2024
     */
    int updateByExampleSelective(@Param("row") LogInfo row, @Param("example") LogInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_info
     *
     * @mbg.generated Wed Nov 13 23:35:15 GMT 2024
     */
    int updateByExample(@Param("row") LogInfo row, @Param("example") LogInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_info
     *
     * @mbg.generated Wed Nov 13 23:35:15 GMT 2024
     */
    int updateByPrimaryKeySelective(LogInfo row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table log_info
     *
     * @mbg.generated Wed Nov 13 23:35:15 GMT 2024
     */
    int updateByPrimaryKey(LogInfo row);
}