package com.example.hotel.controller;


import com.example.hotel.common.base.ResponseResult;
import com.example.hotel.dto.request.AddHotelRequestDTO;
import com.example.hotel.dto.request.BookRoomRequestDTO;
import com.example.hotel.dto.request.CancelOrderRequestDTO;
import com.example.hotel.dto.request.DeleteHotelInfoRequestDTO;
import com.example.hotel.dto.request.ModifyHotelInfoRequestDTO;
import com.example.hotel.dto.request.ModifyOrderInfoRequestDTO;
import com.example.hotel.dto.request.QueryHotelRequestDTO;
import com.example.hotel.dto.request.QueryOrderDetailRequestDTO;
import com.example.hotel.dto.response.AvailableHotelResponse;
import com.example.hotel.dto.response.HotelDetailResponse;
import com.example.hotel.dto.response.OrderDetailInfoResponse;
import com.example.hotel.dto.response.OrderInfoResponse;
import com.example.hotel.entity.Order;
import com.example.hotel.entity.OrderDetail;
import com.example.hotel.enums.OrderStatusEnum;
import com.example.hotel.mapper.OrderMapper;
import com.example.hotel.service.oder.OrderDetailService;
import com.example.hotel.service.oder.OrderService;
import com.example.hotel.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/order")
@Api(tags = "Order API")
public class OrderController {


  @Autowired
  private JwtUtil jwtUtil;
  private OrderMapper orderMapper;
  private OrderService orderService;

  /**
   * book room and create order
   *
   * @return
   */
  @PostMapping("/bookRoom")
  @RequestMapping(value = "bookRoom", method = RequestMethod.POST)
  public ResponseResult bookRoom(@RequestHeader("Authorization") String token,
      @ApiParam(value = "book room", required = true)
      @RequestBody BookRoomRequestDTO requestDTO) {
      return ResponseResult.ofSuccess();
  }

  /**
   * query order detail info(room, room type, customer of each room)
   *
   * @return
   */
  @GetMapping("/queryOrderDetailInfo")
  @RequestMapping(value = "queryOrderDetailInfo", method = RequestMethod.GET)
  public ResponseResult<OrderInfoResponse> queryOrderDetailInfo(
      @RequestHeader("Authorization") String token,
      @ApiParam(value = "query order details ", required = true)
      @RequestBody QueryOrderDetailRequestDTO requestDTO) {

    return ResponseResult.ofSuccess();
  }

  /**
   * modify order info
   *
   * @return
   */
  @PostMapping("/modifyOrderInfo")
  @RequestMapping(value = "modifyOrderInfo", method = RequestMethod.POST)
  public ResponseResult modifyOrderInfo(@RequestHeader("Authorization") String token,
      @ApiParam(value = "order info", required = true)
      @RequestBody ModifyOrderInfoRequestDTO requestDTO) {

    return ResponseResult.ofSuccess();
  }

  @PostMapping("/requestCancelOrder")
  public ResponseResult requestCancelOrder(
          @RequestHeader("Authorization") String token,
          @ApiParam(value = "Order ID", required = true) @RequestParam Long orderId) {

    // Check if the order exists
    Order order = OrderMapper.findOrderById(orderId);
    if (order == null) {
      return ResponseResult.ofError(404L, "Order not found");
    }

    // Record the cancellation request and mark it as awaiting review
    order.setStatus(4);
    orderMapper.updateOrder(order);

    return ResponseResult.ofSuccess("Cancel request submitted, awaiting admin approval.");
  }



  /**
   * query order list
   *
   * @return
   */
  @PostMapping("/queryOrderList")
  @RequestMapping(value = "queryOrderList", method = RequestMethod.POST)
  public ResponseResult<List<OrderDetailInfoResponse>> queryOrderList(@RequestHeader("Authorization") String token,
      @ApiParam(value = "query hotel details ", required = true)
      @RequestBody QueryOrderDetailRequestDTO requestDTO) {
    return ResponseResult.ofSuccess();
  }

  /**
   * User submits order cancellation request
   * @param orderId
   * @param roomNumbers List of room numbers to be cancelled
   * @return Submit Result
   */
  @PostMapping("/{orderId}/cancel/request")
  public ResponseResult<String> requestCancelOrder(
          @PathVariable Long orderId,
          @RequestBody int[] roomNumbers) {
    return orderService.requestCancelOrder(orderId, roomNumbers);
  }

  /**
   * Administrator reviews and cancels order application
   * @param orderId
   * @param approve Approval
   * @return Audit Results
   */
  @PostMapping("/{orderId}/cancel/review")
  public ResponseResult<String> reviewCancelOrder(
          @PathVariable Long orderId,
          @RequestParam boolean approve) {
    return orderService.reviewCancelOrder(orderId, approve);
  }

  private final OrderDetailService orderDetailService;

  public OrderController(OrderDetailService orderDetailService) {
    this.orderDetailService = orderDetailService;
  }

  @GetMapping("/{orderId}/details")
  public List<OrderDetail> getOrderDetails(@PathVariable Long orderId) {
    return orderDetailService.getOrderDetailsByOrderId(orderId);
  }

  @PostMapping("/details/{id}/status")
  public void updateOrderDetailStatus(@PathVariable Long id, @RequestParam Integer status) {
    orderDetailService.updateOrderDetailStatus(id, status);
  }
}
