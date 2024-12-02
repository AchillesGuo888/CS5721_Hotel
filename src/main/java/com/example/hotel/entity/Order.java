package com.example.hotel.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.hotel.common.base.BaseEntity;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 订单
 * @author 言曌
 * @date 2020/4/5 3:25 下午
 */
@Data
@TableName("t_order")
public class Order extends BaseEntity {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 房间ID
     */
    private Long postId;

    /**
     * 数量
     */
    private Integer quantity;

    /**
     * 住客姓名
     */
    private String name;

    /**
     * 联系手机
     */
    private String phone;


    /**
     * 身份证
     */
    private String idCard;

    /**
     * 入店日期
     */
    private String startDate;

    /**
     * order id
     */
    private Long orderId;

    /**
     * 订单状态：0待支付，1已支付，2已完结，3已取消，4申请取消待审核
     */
    private Integer status;

    private Integer price;
    private Integer totalPrice;
    private String postTitle;
    private String postNumber;
    private int earnedPoints;

    private List<Integer> roomNumbers; // the room number list in the order

    // 在OrderDetail类中将pendingCancelRooms改为String类型
    private String pendingCancelRooms; // 存储为逗号分隔的字符串

    // 在插入或更新时，将int[]转换为String
    public void setPendingCancelRooms(int[] rooms) {
        if (rooms != null && rooms.length > 0) {
            this.pendingCancelRooms = Arrays.stream(rooms)
                    .mapToObj(String::valueOf)
                    .collect(Collectors.joining(","));
        } else {
            this.pendingCancelRooms = "";
        }
    }

    // 在获取时将String转换回int[]
    public int[] getPendingCancelRooms() {
        if (pendingCancelRooms != null && !pendingCancelRooms.isEmpty()) {
            return Arrays.stream(pendingCancelRooms.split(","))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
        return new int[0]; // 默认返回空数组
    }


    /**
     * Remaining room numbers (updated after partial cancellation)
     */
    private List<String> remainingRooms;

    /**
     * Order review comments (recorded by the administrator during review)
     */
    private String reviewComment;

    public Long getOrderId() {
        return orderId;
    }

    // Setter
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public List<Integer> getRoomNumbers() {
        return roomNumbers;
    }

    public void setRoomNumbers(List<Integer> roomNumbers) {
        this.roomNumbers = roomNumbers;
    }

    public class OrderStatus {
        public static final int PENDING_PAYMENT = 0;
        public static final int PAID = 1;
        public static final int COMPLETED = 2;
        public static final int CANCELLED = 3;
        public static final int PENDING_APPROVAL = 4;
    }


    /**
     * No-argument constructor
     */
    public Order() {
    }

    /**
     * Full parameter construction method (for easy initialization)
     */
    public Order(Long userId, Long postId, Integer quantity, String name, String phone, String idCard,
                 String startDate, Long orderId, Integer status, Integer price, Integer totalPrice,
                 String postTitle, String postNumber, int earnedPoints) {
        this.userId = userId;
        this.postId = postId;
        this.quantity = quantity;
        this.name = name;
        this.phone = phone;
        this.idCard = idCard;
        this.startDate = startDate;
        this.orderId = orderId;
        this.status = status;
        this.price = price;
        this.totalPrice = totalPrice;
        this.postTitle = postTitle;
        this.postNumber = postNumber;
        this.earnedPoints = earnedPoints;
    }

    /**
     * Getter for earnedPoints
     */
    public int getEarnedPoints() {
        return earnedPoints;
    }

    /**
     * Setter for earnedPoints
     */
    public void setEarnedPoints(int earnedPoints) {
        this.earnedPoints = earnedPoints;
    }

    /**
     * Getter and Setter for remainingRooms
     */
    public List<String> getRemainingRooms() {
        return remainingRooms;
    }

    public void setRemainingRooms(List<String> remainingRooms) {
        this.remainingRooms = remainingRooms;
    }

    /**
     * Getter and Setter for reviewComment
     */
    public String getReviewComment() {
        return reviewComment;
    }

    public void setReviewComment(String reviewComment) {
        this.reviewComment = reviewComment;
    }
}
