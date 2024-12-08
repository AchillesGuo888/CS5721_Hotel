package com.example.hotel.controller;


import com.example.hotel.common.base.ResponseResult;
import com.example.hotel.config.OrderQueryConfig;
import com.example.hotel.dto.request.BookRoomRequestDTO;
import com.example.hotel.dto.request.CancelOrderRequestDTO;
import com.example.hotel.dto.request.ChangeRoomRequestDTO;
import com.example.hotel.dto.request.ModifyOrderInfoRequestDTO;
import com.example.hotel.dto.request.PayBillRequestDTO;
import com.example.hotel.dto.request.PayDifferenceRequestDTO;
import com.example.hotel.dto.request.PrebookRoomRequestDTO;
import com.example.hotel.dto.request.QueryChangeRoomRequestDTO;
import com.example.hotel.dto.request.QueryOrderDetailRequestDTO;
import com.example.hotel.dto.response.ChangeOrderRoomCountResponse;
import com.example.hotel.dto.response.OrderInfoListResponse;
import com.example.hotel.dto.response.OrderInfoResponse;
import com.example.hotel.dto.response.PreBookRoomResponse;
import com.example.hotel.dto.response.QueryChangeEmptyRoomResponse;
import com.example.hotel.dto.response.RoomAndTypeWithPriceResponse;
import com.example.hotel.exception.BizException;
import com.example.hotel.service.order.OrderInfoService;
import com.example.hotel.service.order.OrderQueryService;
import com.github.pagehelper.PageSerializable;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/order")
@Api(tags = "Order API")
@AllArgsConstructor
public class OrderController {

  private final OrderInfoService orderInfoService;

  private final OrderQueryService orderQueryService;


  /**
   * book room and create order
   *
   * @return
   */
  @PostMapping("/bookRoom")
  @RequestMapping(value = "bookRoom", method = RequestMethod.POST)
  public ResponseResult<Boolean> bookRoom(@RequestHeader("Authorization") String token,
      @ApiParam(value = "book room", required = true)
      @RequestBody BookRoomRequestDTO requestDTO) {
    try {
      return ResponseResult.ofSuccess(orderInfoService.bookRoom(requestDTO, token));
    } catch (BizException e) {
      log.error("Book room error", e);
      return ResponseResult.ofError(e.getCode().getCode(), e.getMessage());
    }
  }

  /**
   * query order detail info(room, room type, customer of each room)
   *
   * @return
   */
  @GetMapping("/queryOrderDetailInfo/{id}")
  @RequestMapping(value = "queryOrderDetailInfo", method = RequestMethod.GET)
  public ResponseResult<OrderInfoResponse> queryOrderDetailInfo(
      @ApiParam(value = "order id ", required = true)
      @PathVariable Long id) {

    try {

      return ResponseResult.ofSuccess(orderQueryService.queryOrderDetail(id));
    } catch (BizException e) {
      log.error("Query order detail error", e);
      return ResponseResult.ofError(e.getCode().getCode(), e.getMessage());
    }
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
    try {
      return ResponseResult.ofSuccess(orderInfoService.modifyOrder(requestDTO));
    } catch (BizException e) {
      log.error("Query order detail error", e);
      return ResponseResult.ofError(e.getCode().getCode(), e.getMessage());
    }
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

    return ResponseResult.ofSuccess();
  }

  /**
   * query order list
   *
   * @return
   */
  @PostMapping("/queryOrderList")
  @RequestMapping(value = "queryOrderList", method = RequestMethod.POST)
  public ResponseResult<PageSerializable<OrderInfoListResponse>> queryOrderList(
      @RequestHeader("Authorization") String token,
      @ApiParam(value = "query hotel details ", required = true)
      @RequestBody QueryOrderDetailRequestDTO requestDTO) {
    try {
      return ResponseResult.ofSuccess(orderInfoService.queryOrderList(requestDTO, token));
    } catch (BizException e) {
      log.error("Query order detail error", e);
      return ResponseResult.ofError(e.getCode().getCode(), e.getMessage());
    }
  }

  /**
   * pre-generate the order info (showt the price and discount)
   *
   * @return
   */
  @PostMapping("/pregenerateOrder")
  @RequestMapping(value = "pregenerateOrder", method = RequestMethod.POST)
  public ResponseResult<PreBookRoomResponse> pregenerateOrder(
      @RequestHeader("Authorization") String token,
      @ApiParam(value = "pre-generate order", required = true)
      @RequestBody PrebookRoomRequestDTO requestDTO) {
    try {
      return ResponseResult.ofSuccess(orderInfoService.pregenerateOrder(requestDTO, token));
    } catch (BizException e) {
      log.error("modify user info error", e);
      return ResponseResult.ofError(e.getCode().getCode(), e.getMessage());
    }
  }

  /**
   * add a room count in order view (show the price and discount)
   *
   * @return
   */
  @PostMapping("/addOrderRoom")
  @RequestMapping(value = "addOrderRoom", method = RequestMethod.POST)
  public ResponseResult<ChangeOrderRoomCountResponse> addOrderRoom(
      @RequestHeader("Authorization") String token,
      @ApiParam(value = "pre-generate order", required = true)
      @RequestBody PrebookRoomRequestDTO requestDTO) {
    try {
      return ResponseResult.ofSuccess(orderInfoService.addOrderRoom(requestDTO, token));
    } catch (BizException e) {
      log.error("modify user info error", e);
      return ResponseResult.ofError(e.getCode().getCode(), e.getMessage());
    }
  }

  /**
   * add a room count in order view (show the price and discount)
   *
   * @return
   */
  @PostMapping("/removeOrderRoom")
  @RequestMapping(value = "removeOrderRoom", method = RequestMethod.POST)
  public ResponseResult<ChangeOrderRoomCountResponse> removeOrderRoom(
      @RequestHeader("Authorization") String token,
      @ApiParam(value = "pre-generate order", required = true)
      @RequestBody PrebookRoomRequestDTO requestDTO) {
    try {
      return ResponseResult.ofSuccess(orderInfoService.removeOrderRoom(requestDTO, token));
    } catch (BizException e) {
      log.error("modify user info error", e);
      return ResponseResult.ofError(e.getCode().getCode(), e.getMessage());
    }
  }

  /**
   * pay room bill
   *
   * @return
   */
  @PostMapping("/payBill")
  @RequestMapping(value = "payBill", method = RequestMethod.POST)
  public ResponseResult<Boolean> payRoomBill(@RequestHeader("Authorization") String token,
      @ApiParam(value = "bill detail", required = true) @RequestBody PayBillRequestDTO requestDTO) {
    try {
      return ResponseResult.ofSuccess(orderInfoService.payBill(token,requestDTO));
    } catch (BizException e) {
      log.error("user login error", e);
      return ResponseResult.ofError(e.getCode().getCode(), e.getMessage());
    }

  }

  /**
   * pay update room difference
   *
   * @return
   */
  @PostMapping("/payDifference")
  @RequestMapping(value = "payDifference", method = RequestMethod.POST)
  public ResponseResult payRoomDifference(@RequestHeader("Authorization") String token,
      @ApiParam(value = "difference bill detail", required = true) @RequestBody PayDifferenceRequestDTO requestDTO) {
    return ResponseResult.ofSuccess();
  }

  /**
   * change room
   *
   * @return
   */
  @PostMapping("/changeRoom")
  @RequestMapping(value = "changeRoom", method = RequestMethod.POST)
  public ResponseResult changeRoom(@RequestHeader("Authorization") String token,
      @ApiParam(value = "change room", required = true)
      @RequestBody ChangeRoomRequestDTO requestDTO) {
    try {
      orderInfoService.changeRoom(token,requestDTO);
      return ResponseResult.ofSuccess();
    } catch (BizException e) {
      log.error("user login error", e);
      return ResponseResult.ofError(e.getCode().getCode(), e.getMessage());
    }
  }

  @PostMapping("/queryEmptyRoom")
  @RequestMapping(value = "queryEmptyRoom", method = RequestMethod.POST)
  public ResponseResult<List<QueryChangeEmptyRoomResponse>> queryEmptyRoom(@RequestHeader("Authorization") String token,
      @ApiParam(value = "change room", required = true)
      @RequestBody QueryChangeRoomRequestDTO requestDTO) {
    try {
      return ResponseResult.ofSuccess(orderInfoService.queryEmptyRoom(token,requestDTO));
    } catch (BizException e) {
      log.error("user login error", e);
      return ResponseResult.ofError(e.getCode().getCode(), e.getMessage());
    }

  }


}
