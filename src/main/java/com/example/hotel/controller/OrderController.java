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
import com.example.hotel.enums.OrderStatusEnum;
import com.example.hotel.mapper.OrderMapper;
import com.example.hotel.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/order")
@Api(tags = "Order API")
public class OrderController {


  @Autowired
  private JwtUtil jwtUtil;
  private OrderMapper orderMapper;

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

  /**
   * cancel order
   *
   * @return
   */
  @DeleteMapping("/cancelOrder")
  @RequestMapping(value = "cancelOrder", method = RequestMethod.DELETE)
  public ResponseResult cancelOrder(@RequestHeader("Authorization") String token,
      @ApiParam(value = "cancel order", required = true)
      @RequestBody CancelOrderRequestDTO requestDTO) {
    Order order = orderMapper.findOrderById(requestDTO.getOrderId());
    if(order == null){
      //订单未找到，返回错误响应
      return ResponseResult.ofError(404L,"Oder not found");
    }

    //更新订单状态为已取消
    orderMapper.cancelOrder(requestDTO.getOrderId(), OrderStatusEnum.CANCELLED.getCode());
    return ResponseResult.ofSuccess();
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


}
