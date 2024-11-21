package com.example.hotel.controller;


import com.example.hotel.common.base.ResponseResult;
import com.example.hotel.controller.refund.RefundOrderStatusObserver;
import com.example.hotel.controller.refund.RefundPointsObserver;
import com.example.hotel.controller.refund.RefundSubject;
import com.example.hotel.dto.request.*;
import com.example.hotel.dto.response.AvailableHotelResponse;
import com.example.hotel.dto.response.HotelDetailResponse;
import com.example.hotel.entity.Order;
import com.example.hotel.exception.OrderNotFoundException;
import com.example.hotel.mapper.OrderMapper;
import com.example.hotel.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/bill")
@Api(tags = "Bill API")
public class BillController {


  @Autowired
  private JwtUtil jwtUtil;

  /**
   * pay room bill
   *
   * @return
   */
  @PostMapping("/payRoomBill")
  @RequestMapping(value = "payRoomBill", method = RequestMethod.POST)
  public ResponseResult payRoomBill(@RequestHeader("Authorization") String token,
      @ApiParam(value = "bill detail", required = true) @RequestBody PayBillRequestDTO requestDTO) {
      return ResponseResult.ofSuccess();
  }

  /**
   * pay update room difference
   *
   * @return
   */
  @PostMapping("/payRoomDifference")
  @RequestMapping(value = "payRoomDifference", method = RequestMethod.POST)
  public ResponseResult payRoomDifference(@RequestHeader("Authorization") String token,
      @ApiParam(value = "difference bill detail", required = true) @RequestBody PayDifferenceRequestDTO requestDTO) {
    return ResponseResult.ofSuccess();
  }

  /**
   * Refund room bill
   *
   * @return
   */
  @PostMapping("/refund")
  @RequestMapping(value = "refund", method = RequestMethod.POST)
  public ResponseResult refundBill(@RequestHeader("Authorization") String token,
                                   @ApiParam(value = "refund request detail", required = true) @RequestBody RefundRequestDTO refundRequestDTO) {
    // Authenticate admin user
    if (!jwtUtil.isAdmin(token)) {
      return ResponseResult.ofError(403L, "Only admin can process refunds.");
    }

    // Validate if the order is refundable
    boolean isRefundable = checkOrderRefundable(refundRequestDTO.getOrderId());
    if (!isRefundable) {
      return ResponseResult.ofError(400L, "This order is not refundable.");
    }

    // Get the order details
    Order order = getOrderDetails(refundRequestDTO.getOrderId());
    if (order == null) {
      return ResponseResult.ofError(404L, "Order not found.");
    }

    // Refund logic
    RefundSubject refundSubject = new RefundSubject();

    // Add observers (listeners)
    refundSubject.addObserver(new RefundOrderStatusObserver());
    refundSubject.addObserver(new RefundPointsObserver());

    // Execute refund and notify observers
    refundSubject.refund(order);

    // Return success
    return ResponseResult.ofSuccess("Refund processed successfully.");
  }

  private Order getOrderDetails(Long orderId) {
    // Fetch order details from the database (example logic)
    Order order = OrderMapper.findOrderById(orderId);
    if (order == null) {
      throw new OrderNotFoundException("Order not found for id: " + orderId);  // Throws an exception if no order is found
    }
    return order;
  }

  private boolean checkOrderRefundable(Long orderId) {
    // Implement logic to check if the order is refundable
    return true; // Placeholder
  }

  private void processRefund(Long orderId, double amount) {
    // Implement the logic to process refund with payment provider
  }

  private void updateOrderStatus(Long orderId, String status) {
    // Implement the logic to update the order status in the database
  }

  private void rollbackUserPoints(Long userId, int points) {
    // Implement the logic to roll back the user's points
  }


}
