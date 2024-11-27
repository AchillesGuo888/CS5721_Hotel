package com.example.hotel.service.oder;

import com.example.hotel.common.base.ResponseResult;
import com.example.hotel.entity.Order;
import com.example.hotel.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    /**
     * Update order status
     * @param orderId
     * @param status New order status
     * @return Number of rows affected
     */
    public boolean updateOrderStatus(Long orderId, String status) {
        if (orderId == null || status == null || status.isEmpty()) {
            throw new IllegalArgumentException("Invalid order ID or status.");
        }

        int rowsAffected = orderMapper.updateOrderStatus(orderId, status);
        return rowsAffected > 0; // Returns whether the record has been updated
    }

    /**
     * User submits order cancellation request
     *
     * @param orderId
     * @param roomNumbersToCancel The room number that want to cancel
     * @return Application Result
     */
    public ResponseResult<String> requestCancelOrder(Long orderId, int[] roomNumbersToCancel) {
        // Find the order
        Order order = OrderMapper.findOrderById(orderId);
        if (order == null) {
            return ResponseResult.ofError(404L, "Order not found.");
        }

        // Check whether the cancellation conditions are met
        LocalDateTime checkInTime = calculateCheckInTime(order.getStartDate());
        LocalDateTime currentTime = LocalDateTime.now();
        if (currentTime.isAfter(checkInTime.minusHours(24))) {
            return ResponseResult.ofError(400L, "Cancellation is not allowed within 24 hours of check-in time.");
        }

        // Check if the room belongs to the order
        if (!validateRooms(order, roomNumbersToCancel)) {
            return ResponseResult.ofError(400L, "Some rooms are not part of this order.");
        }

        // Mark order as "pending review"
        order.setStatus(4); // 4 Indicates that the application is cancelled and pending review
        order.setPendingCancelRooms(roomNumbersToCancel); // Record rooms to be cancelled
        orderMapper.updateOrder(order);

        return ResponseResult.ofSuccess("Cancellation request submitted successfully. Awaiting admin approval.");
    }

    /**
     * Administrator reviews and cancels the application
     *
     * @param orderId
     * @param approve Approval
     * @return Application Result
     */
    public ResponseResult<String> reviewCancelOrder(Long orderId, boolean approve) {
        // Find the order
        Order order = OrderMapper.findOrderById(orderId);
        if (order == null) {
            return ResponseResult.ofError(404L, "Order not found.");
        }

        if (order.getStatus() != 4) { // Check if it is in the "Awaiting Review" status
            return ResponseResult.ofError(400L, "Order is not in a pending review state.");
        }

        if (approve) {
            // Get the list of rooms to be cancelled
            int[] pendingCancelRoomsArray = order.getPendingCancelRooms();
            if (pendingCancelRoomsArray == null || pendingCancelRoomsArray.length == 0) {
                return ResponseResult.ofError(400L, "No rooms pending cancellation.");
            }

            // Convert int[] to List<Integer>
            List<Integer> pendingCancelRooms = Arrays.stream(pendingCancelRoomsArray)
                    .boxed()
                    .collect(Collectors.toList());

            // Execute room cancellation logic
            cancelRooms(order, pendingCancelRooms);

            // Clear pending room cancellation records
            order.setPendingCancelRooms(null);

            // Update order status
            if (order.getRoomNumbers().isEmpty()) {
                order.setStatus(3); // Status 3: Cancelled
            } else {
                order.setStatus(1); // Status 1: Still valid after partial cancellation
            }

            orderMapper.updateOrder(order);

            return ResponseResult.ofSuccess("Cancellation approved and executed successfully.");
        } else {
            return ResponseResult.ofError(403L, "Cancellation not approved by the administrator.");
        }
    }


    /**
     * Calculate the check-in time of the order (12 noon every day)
     *
     * @param startDate Check-in date (format: yyyy-MM-dd)
     * @return Check-in time
     */
    private LocalDateTime calculateCheckInTime(String startDate) {
        LocalDate checkInDate = LocalDate.parse(startDate);
        LocalTime checkInHour = LocalTime.of(12, 0); // 12 noon
        return LocalDateTime.of(checkInDate, checkInHour);
    }

    /**
     * Check if the room is in the order
     *
     * @param order
     * @param roomNumbers Room Number List
     * @return If are all valid
     */
    private boolean validateRooms(Order order, int[] roomNumbers) {
        if (order == null || order.getRoomNumbers() == null) {
            return false; // When the order or room list is empty, return false directly
        }
        return Arrays.stream(roomNumbers).allMatch(order.getRoomNumbers()::contains);
    }


    /**
     * Cancel a room in the reservation
     *
     * @param order
     * @param roomNumbers Room Number List
     */
    private void cancelRooms(Order order, List<Integer> roomNumbers) {
        roomNumbers.forEach(room -> order.getRoomNumbers().remove(room)); // Remove Room
        // If there are no rooms left for the order, mark the order as cancelled
        if (order.getRoomNumbers().isEmpty()) {
            order.setStatus(3); // 3 means the order has been cancelled
        }
    }

}