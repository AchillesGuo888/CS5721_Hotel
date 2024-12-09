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
     * Cancel order
     * @param orderId Order ID
     * @return Whether the cancellation is successful
     */
    @Transactional
    public String processOrderCancellation(Long orderId, Integer isApproved, String cancelReason) {

        isApproved = 1;//Default approval
        // Query order information
        //OrderDetail order = orderDetailMapper.findOrderById(orderId);
        OrderBase orderBase = orderBaseMapper.selectByPrimaryKey(orderId);
        if (orderBase == null) {
            return "Order does not exist";
        }

        // Determine the cancellation status
        if (orderBase.getIsCancelled() == 1)
        {
            return "The order has been canceled and cannot be repeated";
        }

        // Get the current time and check-in time
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime checkInTime = orderBase.getCheckInTime();
        // Determine whether it can be cancelled
        if (checkInTime == null) {
            checkInTime = LocalDateTime.now().plusDays(2);
        }
        long hoursBetween = ChronoUnit.HOURS.between(currentTime, checkInTime);
        if (hoursBetween < 24) {
            return "Cannot be cancelled, less than 24 hours before check-in time";
        }

        // Determine whether the administrator has passed the review
        if (isApproved == 0) {
            return "The administrator's review failed and the cancellation failed.";
        }

        // Execute cancellation operations, including updating order status and points rollback, etc.
        try {
            int rowsAffected = orderDetailMapper.updateOrderStatusToCancelled(orderId);
            if (rowsAffected > 0) {
                // Record cancellation reason
                processOrderCancellationReason(orderId, cancelReason);
                //Mark Cancel
                orderBaseMapper.updateOrderStatusToCancelled(orderId);

                // Refund points
                UserPoints userPoints = pointInfoMapper.findByOrderId(String.valueOf(orderId));
                if (userPoints != null) {
                    userPoints.setIsDeleted((byte) 1);
                    userPoints.setPoints(0);
                    pointInfoMapper.updateUserPoints(userPoints);
                }

                // Record refund information to the payment_info table
                PaymentInfo paymentInfo = new PaymentInfo();
                paymentInfo.setOrderId(orderId);
                BigDecimal zero = new BigDecimal(0);
                BigDecimal price = orderBase.getRealPrice();
                BigDecimal refund = zero.subtract(price);
                paymentInfo.setAmount(refund); // The refund amount is negative

                // Insert refund information
                paymentInfoMapper.insertSelective(paymentInfo);

                //Record refund information
                RefundInfo refundInfo = new RefundInfo();
                refundInfo.setOrderId(orderId);
                refundInfo.setRefundAmount(orderBase.getRealPrice()); //Assume the price field is `price`
                refundInfo.setRefundStatus(Byte.valueOf("1")); //Assume 1 means success
                refundInfoMapper.insertRefundInfo(refundInfo);

                return "Order canceled successfully, points rolled back, refund processed";            }
        }catch (Exception e) {
            // Capture and record exceptions
            logger.error("Exception occurred when canceling the order, order ID: {}, error: {}", orderId, e.getMessage());
            return "Order cancellation failed, system internal error";
        }

        return "Order cancellation failed";
    }


    //Method for recording cancellation reasons
    public String processOrderCancellationReason(Long orderId, String cancelReason) {
        //Check whether the order exists
        RefundInfo refundInfo = refundInfoMapper.selectByPrimaryKey(orderId);
        if (refundInfo == null) {
            logger.warn("Order does not exist, cannot record cancellation reason, order ID: {}", orderId);
            return "Order does not exist, cannot record cancellation reason";
        }

        // Update cancellation reason
        refundInfo.setRefundReason(cancelReason);
        refundInfo.setUpdatedAt(LocalDateTime.now());
        refundInfoMapper.updateRefundInfo(orderId, refundInfo.getRefundStatus(), cancelReason, refundInfo.getUpdatedAt());

        // Print log
        logger.info("Update the cancellation reason of the refund record, order ID: {}, reason: {}", orderId, cancelReason);
        return "Cancellation reason record successful";
    }

    @Transactional
    public String cancelRoomInOrder(Long orderId, Long roomNumber, String cancelReason, int isApproved) {
        isApproved = 1;

        // Find qualified orders
        OrderDetail orderDetail = orderDetailMapper.findOrderByOrderIdAndRoom(orderId, roomNumber);
        if (orderDetail == null) {
            throw new RuntimeException("Order does not exist");
        }

        if(orderDetail.getStatus() == 1){
            throw new RuntimeException("Order has been canceled");
        }

        // Determine whether the administrator has passed the review
        if (isApproved == 0) {
            throw new RuntimeException("Administrator review failed, cancellation failed");
        }

        // Register all users with user_type=1 as observers
        observerManagerService.addAllType1UsersAsObservers();

        // Notify all observers
        observerManagerService.notifyAllObservers(orderDetail);

        System.out.println("Order cancellation operation completed, and all relevant administrators notified.");

        ObserverManager observerManager = new ObserverManager();
        // Notify all administrators
        String message = "Order ID " + orderId + "中:Room" + roomNumber + "Cancelled";
        observerManager.notifyObservers(orderDetail);

        // Cancel the specified room
        orderDetailMapper.updateRoomStatusByRoomNumber(orderId, roomNumber, 1); // Update the specified room status to canceled

        // Check if there are any active rooms for the order
        OrderDetailExample detailExample = new OrderDetailExample();
        OrderDetailExample.Criteria detailCr = detailExample.createCriteria();
        detailCr.andOrderIdEqualTo(orderId).andStatusEqualTo((byte)0);
        long count = orderDetailMapper.countByExample(detailExample);
        if (count == 0) {
            orderBaseMapper.updateOrderStatusToCancelled(orderId); // If there is no active room, cancel the entire order
        }

        //Record cancellation reason
        processOrderCancellationReason(orderId, cancelReason);

        //Record refund information to payment_info table
        PaymentInfo paymentInfo = new PaymentInfo();
        paymentInfo.setOrderId(orderId);
        paymentInfo.setAmount(orderDetail.getPrice().negate()); //Refund amount is negative

        //Insert refund information
        paymentInfoMapper.insertSelective(paymentInfo);

        //Record refund information
        RefundInfo refundInfo = new RefundInfo();
        refundInfo.setOrderId(orderId);
        refundInfo.setRefundAmount(orderDetail.getPrice()); // Assume the price field is `price`
        refundInfo.setRefundStatus(Byte.valueOf("1")); // Assume 1 means success
        refundInfoMapper.insertRefundInfo(refundInfo);

        return "Order canceled successfully, points rolled back, refund processed";
    }

    public OrderDetail getOrderDetailById(Long orderId) {
        return orderDetailMapper.findOrderById(orderId);
    }

}
