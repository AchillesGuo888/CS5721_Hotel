package com.example.hotel.service.order;


import com.example.hotel.dto.request.BookRoomRequestDTO;
import com.example.hotel.dto.request.ChangeRoomRequestDTO;
import com.example.hotel.dto.request.ModifyOrderInfoRequestDTO;
import com.example.hotel.dto.request.PayBillRequestDTO;
import com.example.hotel.dto.request.PrebookRoomRequestDTO;
import com.example.hotel.dto.request.QueryChangeRoomRequestDTO;
import com.example.hotel.dto.request.QueryOrderDetailRequestDTO;
import com.example.hotel.dto.response.ChangeOrderRoomCountResponse;
import com.example.hotel.dto.response.OrderInfoListResponse;
import com.example.hotel.dto.response.OrderInfoResponse;
import com.example.hotel.dto.response.PreBookRoomResponse;
import com.example.hotel.dto.response.QueryChangeEmptyRoomResponse;
import com.example.hotel.exception.BizException;
import com.github.pagehelper.PageSerializable;
import java.util.List;


public interface OrderInfoService {

  Boolean bookRoom(BookRoomRequestDTO requestDTO, String token) throws BizException;

  PreBookRoomResponse pregenerateOrder(PrebookRoomRequestDTO requestDTO, String token)
      throws BizException;

  ChangeOrderRoomCountResponse removeOrderRoom(PrebookRoomRequestDTO requestDTO, String token)
      throws BizException;

  ChangeOrderRoomCountResponse addOrderRoom(PrebookRoomRequestDTO requestDTO, String token)
      throws BizException;

  OrderInfoResponse queryOrderDetail(Long id) throws BizException;

  Boolean modifyOrder(ModifyOrderInfoRequestDTO requestDTO) throws BizException;

  PageSerializable<OrderInfoListResponse> queryOrderList(QueryOrderDetailRequestDTO requestDTO,
      String token) throws BizException;

  void scheduleFinishOrder();

  Boolean payBill(String token, PayBillRequestDTO requestDTO)throws BizException;

  List<QueryChangeEmptyRoomResponse> queryEmptyRoom(String token, QueryChangeRoomRequestDTO requestDTO)
      throws BizException;

  void changeRoom(String token, ChangeRoomRequestDTO requestDTO) throws BizException;
  void cancelOrderById(Long orderId);

  void cancelRoomInOrder(Long orderId, Long roomNumber);
}
