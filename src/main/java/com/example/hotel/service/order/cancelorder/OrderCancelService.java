package com.example.hotel.service.order.cancelorder;

import com.example.hotel.entity.*;
import com.example.hotel.mapper.*;
import com.example.hotel.observer.ObserverManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@AllArgsConstructor
@Service
public class OrderCancelService {

    private OrderDetailMapper orderDetailMapper;
    private PointInfoMapper pointInfoMapper;
    private RefundInfoMapper refundInfoMapper;
    private PaymentInfoMapper paymentInfoMapper;
    private UserMapper userMapper;
    private ObserverManager observerManager;

    private static final Logger logger = LoggerFactory.getLogger(OrderCancelService.class);

    /**
     * 取消订单
     * @param orderId 订单ID
     * @return 取消是否成功
     */
    @Transactional
    public String processOrderCancellation(Long orderId, Integer isApproved, String cancelReason) {
        isApproved = 1;//这边手动审核了 订单取消
        // 1. 查询订单信息
        OrderDetail order = orderDetailMapper.findOrderById(orderId);
        if (order == null) {
            return "订单不存在";
        }

        // 2. 获取当前时间和入住时间
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime checkInTime = order.getCheckInTime();

        // 3. 判断是否可以取消
        long hoursBetween = ChronoUnit.HOURS.between(currentTime, checkInTime);
        if (hoursBetween < 24) {
            return "无法取消，距离入住时间不到24小时";
        }

        // 4. 判断管理员是否审核通过
        if (isApproved == 0) {
            return "管理员审核未通过，取消失败";
        }

        // 5. 执行取消操作，包括更新订单状态和积分回滚等
        try {
            int rowsAffected = orderDetailMapper.updateOrderStatusToCancelled(orderId);
            if (rowsAffected > 0) {
                // 记录取消原因
                processOrderCancellationReason(orderId, cancelReason);

                // 退还积分
                UserPoints userPoints = pointInfoMapper.findByOrderId(String.valueOf(orderId));
                if (userPoints != null) {
                    userPoints.setIsDeleted((byte) 1);
                    userPoints.setPoints(0);
                    pointInfoMapper.updateUserPoints(userPoints);
                }

                // 记录退款信息到 payment_info 表
                PaymentInfo paymentInfo = new PaymentInfo();
                paymentInfo.setOrderId(orderId);
                paymentInfo.setAmount(order.getPrice().negate()); // 退款金额为负数

                // 插入退款信息
                paymentInfoMapper.insertPaymentInfo(paymentInfo);

                // 记录退款信息
                RefundInfo refundInfo = new RefundInfo();
                refundInfo.setOrderId(orderId);
                refundInfo.setRefundAmount(order.getPrice()); // 假设价格字段是 `price`
                refundInfo.setRefundStatus(Byte.valueOf("1")); // 假设 1 表示成功
                refundInfoMapper.insertRefundInfo(refundInfo);

                return "订单取消成功，积分已回滚，退款已处理";
            }
        } catch (Exception e) {
            // 捕获并记录异常
            logger.error("取消订单时出现异常，订单ID: {}, 错误: {}", orderId, e.getMessage());
            return "订单取消失败，系统内部错误";
        }

        return "订单取消失败";
    }


    // 记录取消原因的方法
    public String processOrderCancellationReason(Long orderId, String cancelReason) {
        // 查询订单是否存在
        RefundInfo refundInfo = refundInfoMapper.selectByPrimaryKey(orderId);
        if (refundInfo == null) {
            logger.warn("订单不存在，无法记录取消原因，订单ID: {}", orderId);
            return "订单不存在，无法记录取消原因";
        }

        // 更新取消原因
        refundInfo.setRefundReason(cancelReason);
        refundInfo.setUpdatedAt(LocalDateTime.now());
        refundInfoMapper.updateRefundInfo(orderId, refundInfo.getRefundStatus(), cancelReason, refundInfo.getUpdatedAt());

        // 打印日志
        logger.info("更新退款记录的取消原因，订单ID: {}, 原因: {}", orderId, cancelReason);
        return "取消原因记录成功";
    }

    @Transactional
    public String cancelRoomsInOrder(Long orderId, List<Integer> roomNumbers, String cancelReason, int isApproved) {
        // 查询订单信息
        OrderDetail order = orderDetailMapper.findOrderById(orderId);
        if (order == null) {
            throw new RuntimeException("订单不存在");
        }

        // 获取当前时间和入住时间
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime checkInTime = LocalDateTime.now();

        // 判断是否可以取消
        long hoursBetween = ChronoUnit.HOURS.between(currentTime, checkInTime);
        if (hoursBetween > 24) {
            throw new RuntimeException("无法取消，距离入住时间不到24小时");
        }

        // 判断管理员是否审核通过
        if (isApproved == 0) {
            throw new RuntimeException("管理员审核未通过，取消失败");
        }

        // 更新 usertype 为 1 的用户的 status
        List<User> users = userMapper.findUsersByType(1);
        orderDetailMapper.deleteById(orderId);
        for (User user : users) {
            user.setApproveStatus("订单ID: " + order.getId() + " 已取消");
            userMapper.updateUserStatus(user);
        }

        // 取消指定房间
        orderDetailMapper.updateRoomStatusByRoomNumbers(orderId, roomNumbers, 1); // 更新指定房间状态为已取消

        // 检查订单是否还有活跃房间
        int activeRooms = orderDetailMapper.countActiveRoomsByOrderId(orderId);
        if (activeRooms == 0) {
            orderDetailMapper.updateOrderStatusToCancelled(orderId); // 如果没有活跃房间，则取消整个订单
        }

        // 记录取消原因
        processOrderCancellationReason(orderId, cancelReason);

        // 退还积分
        UserPoints userPoints = pointInfoMapper.findByOrderId(String.valueOf(orderId));
        if (userPoints != null) {
            userPoints.setIsDeleted((byte) 1);
            userPoints.setPoints(0);
            pointInfoMapper.updateUserPoints(userPoints);
        }

        // 记录退款信息到 payment_info 表
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setOrderId(orderId);
        paymentInfo.setAmount(order.getPrice().negate()); // 退款金额为负数

        // 插入退款信息
        paymentInfoMapper.insertPaymentInfo(paymentInfo);

        // 记录退款信息
        RefundInfo refundInfo = new RefundInfo();
        refundInfo.setOrderId(orderId);
        refundInfo.setRefundAmount(order.getPrice()); // 假设价格字段是 `price`
        refundInfo.setRefundStatus(Byte.valueOf("1")); // 假设 1 表示成功
        refundInfoMapper.insertRefundInfo(refundInfo);

        return "订单取消成功，积分已回滚，退款已处理";
    }

    public OrderDetail getOrderDetailById(Long orderId) {
        return orderDetailMapper.findOrderById(orderId);
    }

}
