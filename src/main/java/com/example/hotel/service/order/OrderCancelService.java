package com.example.hotel.service.order;

import cn.hutool.db.sql.Order;
import com.example.hotel.controller.refund.AdminService;
import com.example.hotel.entity.OrderDetail;
import com.example.hotel.entity.RefundInfo;
import com.example.hotel.entity.UserPoints;
import com.example.hotel.mapper.OrderDetailMapper;
import com.example.hotel.mapper.PointInfoMapper;
import com.example.hotel.mapper.RefundInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderCancelService {
    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private PointInfoMapper pointInfoMapper;

    @Autowired
    private RefundInfoMapper refundInfoMapper;

    /**
     * 取消订单
     * @param orderId 订单ID
     * @return 取消是否成功
     */
    public String processOrderCancellation(Long orderId, boolean isApproved) {
        // 1. 查询订单信息，获取入住时间
        OrderDetail order = orderDetailMapper.findOrderById(orderId);

        if (order == null) {
            return "订单不存在";
        }

        // 2. 获取当前时间和入住时间
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime checkInTime = order.getCheckInTime();

        // 3. 判断是否可以取消
        long hoursBetween = ChronoUnit.HOURS.between(currentTime, checkInTime);

        // 如果当前时间距离入住时间不到24小时，则不能取消
        if (hoursBetween < 24) {
            return "无法取消，距离入住时间不到24小时";
        }

        // 4. 判断管理员是否审核通过
        if (!isApproved) {
            return "管理员审核未通过，取消失败";
        }

        // 5. 执行取消操作，包括更新订单状态和积分回滚等
        int rowsAffected = orderDetailMapper.updateOrderStatusToCancelled(orderId);

        if (rowsAffected > 0) {
            // 退还积分
            UserPoints userPoints = pointInfoMapper.findByOrderId(String.valueOf(orderId));
            if (userPoints != null) {
                userPoints.setIsDeleted((byte) 1);
                userPoints.setPoints(0);
                pointInfoMapper.updateUserPoints(userPoints);
            }

            // 记录退款信息
            RefundInfo refundInfo = new RefundInfo();
            refundInfo.setOrderId(orderId);
            refundInfo.setRefundAmount(order.getPrice()); // 假设价格字段是 `price`
            refundInfo.setRefundStatus(Byte.valueOf("成功"));
            refundInfoMapper.insertRefundInfo(refundInfo);

            return "订单取消成功，积分已回滚，退款已处理";
        }

        return "订单取消失败";
    }

    public void processOrderCancellationReason(Long orderId, String cancelReason) {
        // 使用 cancelReason 逻辑处理
        System.out.println("取消订单ID: " + orderId + ", 原因: " + cancelReason);
    }

    private List<AdminService> observers = new ArrayList<>();

    // 添加观察者
    public void addObserver(AdminService adminService) {
        observers.add(adminService);
    }

    // 通知所有观察者
    public void notifyObservers(Order order) {
        for (AdminService observer : observers) {
            observer.update(String.valueOf(order));  // 假设 AdminService 有一个 update 方法
        }
    }

    @Transactional
    public void cancelOrderById(Long orderId) {
        // 取消整个订单
        orderDetailMapper.updateOrderStatusToCancelled(orderId);
        orderDetailMapper.updateRoomStatusByOrderId(orderId, 1); // 更新所有房间状态为已取消
    }

    @Transactional
    public void cancelRoomsInOrder(Long orderId, List<Integer> roomNumbers) {
        // 取消部分房间
        orderDetailMapper.updateRoomStatusByRoomNumbers(orderId, roomNumbers, 1); // 更新指定房间状态为已取消

        // 检查订单是否还有活跃房间
        int activeRooms = orderDetailMapper.countActiveRoomsByOrderId(orderId);
        if (activeRooms == 0) {
            orderDetailMapper.updateOrderStatusToCancelled(orderId); // 如果没有活跃房间，则取消整个订单
        }
    }
}
