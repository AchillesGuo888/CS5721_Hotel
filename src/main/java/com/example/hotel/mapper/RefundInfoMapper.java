package com.example.hotel.mapper;

import com.example.hotel.entity.RefundInfo;
import com.example.hotel.entity.RefundInfoExample;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface RefundInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table refund_info
     *
     * @mbg.generated Fri Dec 06 03:08:55 GMT 2024
     */
    long countByExample(RefundInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table refund_info
     *
     * @mbg.generated Fri Dec 06 03:08:55 GMT 2024
     */
    int deleteByExample(RefundInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table refund_info
     *
     * @mbg.generated Fri Dec 06 03:08:55 GMT 2024
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table refund_info
     *
     * @mbg.generated Fri Dec 06 03:08:55 GMT 2024
     */
    int insert(RefundInfo row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table refund_info
     *
     * @mbg.generated Fri Dec 06 03:08:55 GMT 2024
     */
    int insertSelective(RefundInfo row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table refund_info
     *
     * @mbg.generated Fri Dec 06 03:08:55 GMT 2024
     */
    List<RefundInfo> selectByExample(RefundInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table refund_info
     *
     * @mbg.generated Fri Dec 06 03:08:55 GMT 2024
     */
    RefundInfo selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table refund_info
     *
     * @mbg.generated Fri Dec 06 03:08:55 GMT 2024
     */
    int updateByExampleSelective(@Param("row") RefundInfo row, @Param("example") RefundInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table refund_info
     *
     * @mbg.generated Fri Dec 06 03:08:55 GMT 2024
     */
    int updateByExample(@Param("row") RefundInfo row, @Param("example") RefundInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table refund_info
     *
     * @mbg.generated Fri Dec 06 03:08:55 GMT 2024
     */
    int updateByPrimaryKeySelective(RefundInfo row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table refund_info
     *
     * @mbg.generated Fri Dec 06 03:08:55 GMT 2024
     */
    int updateByPrimaryKey(RefundInfo row);

    @Insert("INSERT INTO refund_info (order_id, refund_status, created_at, updated_at) VALUES (#{orderId}, #{refundStatus}, #{createdAt}, #{updatedAt})")
    int insertRefundInfo(RefundInfo refundInfo);

    @Update("UPDATE refund_info SET refund_status = #{refundStatus}, updated_at = #{updatedAt} WHERE order_id = #{orderId}")
    int updateRefundStatus(@Param("orderId") String orderId, @Param("refundStatus") int refundStatus);

    @Update("UPDATE refund_info " +
            "SET refund_status = #{refundStatus}, refund_reason = #{refundReason}, updated_at = #{updatedAt} " +
            "WHERE order_id = #{orderId}")
    int updateRefundInfo(@Param("orderId") Long orderId,
                         @Param("refundStatus") Byte refundStatus,
                         @Param("refundReason") String refundReason,
                         @Param("updatedAt") LocalDateTime updatedAt);


}