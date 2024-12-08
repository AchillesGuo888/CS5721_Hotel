package com.example.hotel.service.order;


import com.example.hotel.dto.AvailableRoomCountDTO;
import com.example.hotel.dto.request.BookRoomRequestDTO;
import com.example.hotel.dto.request.ChangeRoomRequestDTO;
import com.example.hotel.dto.response.OrderInfoListResponse;
import com.example.hotel.entity.OrderDetail;
import com.example.hotel.exception.BizException;
import java.time.LocalDate;
import java.util.List;


public interface OrderAndDetailService {

  List<AvailableRoomCountDTO> queryAvailableRoomType(List<Long> hotelIds,
      LocalDate startDate, LocalDate endDate, Integer quantity, List<Long> roomTypeIds);

  void addOrderDetail(Long orderBaseId,
      BookRoomRequestDTO requestDTO);

  List<OrderDetail> getOrderDetailByOrderId(List<Long> orderIds);

  List<OrderInfoListResponse> getOrderListInfo(List<Long> orderIds, String userId);

  void dealChangeRoom(ChangeRoomRequestDTO requestDTO, String userId, LocalDate endDate) throws BizException;
}
