package com.example.hotel.mapper;

import com.example.hotel.entity.PaymentInfo;
import com.example.hotel.entity.PaymentInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PaymentInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table payment_info
     *
     * @mbg.generated Thu Nov 14 19:45:39 GMT 2024
     */
    long countByExample(PaymentInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table payment_info
     *
     * @mbg.generated Thu Nov 14 19:45:39 GMT 2024
     */
    int deleteByExample(PaymentInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table payment_info
     *
     * @mbg.generated Thu Nov 14 19:45:39 GMT 2024
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table payment_info
     *
     * @mbg.generated Thu Nov 14 19:45:39 GMT 2024
     */
    int insert(PaymentInfo row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table payment_info
     *
     * @mbg.generated Thu Nov 14 19:45:39 GMT 2024
     */
    int insertSelective(PaymentInfo row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table payment_info
     *
     * @mbg.generated Thu Nov 14 19:45:39 GMT 2024
     */
    List<PaymentInfo> selectByExample(PaymentInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table payment_info
     *
     * @mbg.generated Thu Nov 14 19:45:39 GMT 2024
     */
    PaymentInfo selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table payment_info
     *
     * @mbg.generated Thu Nov 14 19:45:39 GMT 2024
     */
    int updateByExampleSelective(@Param("row") PaymentInfo row, @Param("example") PaymentInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table payment_info
     *
     * @mbg.generated Thu Nov 14 19:45:39 GMT 2024
     */
    int updateByExample(@Param("row") PaymentInfo row, @Param("example") PaymentInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table payment_info
     *
     * @mbg.generated Thu Nov 14 19:45:39 GMT 2024
     */
    int updateByPrimaryKeySelective(PaymentInfo row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table payment_info
     *
     * @mbg.generated Thu Nov 14 19:45:39 GMT 2024
     */
    int updateByPrimaryKey(PaymentInfo row);
}