package com.example.hotel.service.order.cancelorder;

import com.example.hotel.entity.*;
import com.example.hotel.mapper.*;
import com.example.hotel.observer.Observable;
import com.example.hotel.observer.ObserverManager;
import com.example.hotel.observer.ObserverManagerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@AllArgsConstructor
@Service
public class OrderCancelService {

    private OrderDetailMapper orderDetailMapper;
    private OrderBaseMapper orderBaseMapper;
    private PointInfoMapper pointInfoMapper;
    private RefundInfoMapper refundInfoMapper;
    private PaymentInfoMapper paymentInfoMapper;
    private UserMapper userMapper;
    private ObserverManagerService observerManagerService;

    private static final Logger logger = LoggerFactory.getLogger(OrderCancelService.class);

    /**
     * 取消订单
     * @param orderId 订单ID
     * @return 取消是否成功
     */
    @Transactional
    public String processOrderCancellation(Long orderId, Integer isApproved, String cancelReason) {

        isApproved = 1;//Default approval
        // 查询订单信息
        //OrderDetail order = orderDetailMapper.findOrderById(orderId);
        OrderBase orderBase = orderBaseMapper.selectByPrimaryKey(orderId);
        if (orderBase == null) {
            return "订单不存在";
        }

        // 判断取消状态
        if (orderBase.getIsCancelled() == 1)
        {
            return "订单已被取消，不可重复操作";
        }

        // 获取当前时间和入住时间
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime checkInTime = orderBase.getCheckInTime();

        // 判断是否可以取消
        if (checkInTime == null) {
            checkInTime = LocalDateTime.now().plusDays(2);
        }
        long hoursBetween = ChronoUnit.HOURS.between(currentTime, checkInTime);
        if (hoursBetween < 24) {
            return "无法取消，距离入住时间不到24小时";
        }

        // 判断管理员是否审核通过
        if (isApproved == 0) {
            return "管理员审核未通过，取消失败";
        }

        // 执行取消操作，包括更新订单状态和积分回滚等
        try {
            int rowsAffected = orderDetailMapper.updateOrderStatusToCancelled(orderId);
            if (rowsAffected > 0) {
                // 记录取消原因
                processOrderCancellationReason(orderId, cancelReason);
                //标记取消
                orderBaseMapper.updateOrderStatusToCancelled(orderId);

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
                BigDecimal zero = new BigDecimal(0);
                BigDecimal price = orderBase.getRealPrice();
                BigDecimal refund = zero.subtract(price);
                paymentInfo.setAmount(refund); // 退款金额为负数

                // 插入退款信息
                paymentInfoMapper.insertSelective(paymentInfo);

                // 记录退款信息
                RefundInfo refundInfo = new RefundInfo();
                refundInfo.setOrderId(orderId);
                refundInfo.setRefundAmount(orderBase.getRealPrice()); // 假设价格字段是 `price`
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
    public String cancelRoomInOrder(Long orderId, Long roomNumber, String cancelReason, int isApproved) {
        isApproved = 1;

        // 查找符合条件的订单
        OrderDetail orderDetail = orderDetailMapper.findOrderByOrderIdAndRoom(orderId, roomNumber);
        if (orderDetail == null) {
            throw new RuntimeException("订单不存在");
        }

        if(orderDetail.getStatus() == 1){
            throw new RuntimeException("订单已被取消");
        }

        // 判断管理员是否审核通过
        if (isApproved == 0) {
            throw new RuntimeException("管理员审核未通过，取消失败");
        }

        // 注册所有 user_type=1 的用户为观察者
        observerManagerService.addAllType1UsersAsObservers();

        // 通知所有观察者
        observerManagerService.notifyAllObservers(orderDetail);

        System.out.println("订单取消操作完成，并通知了所有相关管理员。");

        ObserverManager observerManager = new ObserverManager();
        // 通知所有管理员
        String message = "订单ID " + orderId + "中:房间" + roomNumber + " 已取消";
        observerManager.notifyObservers(orderDetail);

        // 取消指定房间
        orderDetailMapper.updateRoomStatusByRoomNumber(orderId, roomNumber, 1); // 更新指定房间状态为已取消

        // 检查订单是否还有活跃房间
        OrderDetailExample detailExample = new OrderDetailExample();
        OrderDetailExample.Criteria detailCr = detailExample.createCriteria();
        detailCr.andOrderIdEqualTo(orderId).andStatusEqualTo((byte)0);
        long count = orderDetailMapper.countByExample(detailExample);
        if (count == 0) {
            orderBaseMapper.updateOrderStatusToCancelled(orderId); // 如果没有活跃房间，则取消整个订单
        }

        // 记录取消原因
        processOrderCancellationReason(orderId, cancelReason);

        // 记录退款信息到 payment_info 表
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setOrderId(orderId);
        paymentInfo.setAmount(orderDetail.getPrice().negate()); // 退款金额为负数

        // 插入退款信息
        paymentInfoMapper.insertSelective(paymentInfo);

        // 记录退款信息
        RefundInfo refundInfo = new RefundInfo();
        refundInfo.setOrderId(orderId);
        refundInfo.setRefundAmount(orderDetail.getPrice()); // 假设价格字段是 `price`
        refundInfo.setRefundStatus(Byte.valueOf("1")); // 假设 1 表示成功
        refundInfoMapper.insertRefundInfo(refundInfo);

        return "订单取消成功，积分已回滚，退款已处理";
    }

    public OrderDetail getOrderDetailById(Long orderId) {
        return orderDetailMapper.findOrderById(orderId);
    }

}
