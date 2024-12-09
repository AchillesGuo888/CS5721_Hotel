package com.example.hotel.service.impl.order;

import com.example.hotel.common.base.ResponseCode;
import com.example.hotel.dto.AvailableRoomCountDTO;
import com.example.hotel.dto.DistributableRoomDTO;
import com.example.hotel.dto.request.BookRoomRequestDTO;
import com.example.hotel.dto.request.ChangeRoomRequestDTO;
import com.example.hotel.dto.request.QueryAvailableParam;
import com.example.hotel.dto.response.OrderInfoListResponse;
import com.example.hotel.entity.OrderDetail;
import com.example.hotel.entity.OrderDetailExample;
import com.example.hotel.enums.ChangeRoomTypeEnum;
import com.example.hotel.exception.BizException;
import com.example.hotel.mapper.OrderDetailMapper;
import com.example.hotel.mapper.ext.OrderAndDetailMapperExt;
import com.example.hotel.service.order.OrderAndDetailService;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class OrderAndDetailServiceImpl implements OrderAndDetailService {

  private final OrderAndDetailMapperExt orderAndDetailMapperExt;
  private final OrderDetailMapper orderDetailMapper;

  @Override
  public List<AvailableRoomCountDTO> queryAvailableRoomType(List<Long> hotelIds,
      LocalDate startDate, LocalDate endDate, Integer quantity, List<Long> roomTypeIds) {
    QueryAvailableParam param = new QueryAvailableParam();
    param.setEndDate(endDate);
    param.setHotelIds(hotelIds);
    param.setQuantity(quantity);
    param.setRoomTypeIds(roomTypeIds);
    param.setStartDate(startDate);
    return orderAndDetailMapperExt.getAvailableRoomType(param);
  }

  @Override
  public void addOrderDetail(Long orderBaseId, BookRoomRequestDTO requestDTO) {
    List<DistributableRoomDTO> roomList = orderAndDetailMapperExt
        .getDistributableRoomList(requestDTO.getRoomTypeId(), requestDTO.getHotelId(),
            requestDTO.getStartDate(), requestDTO.getEndDate());
    for (int i = 0; i < requestDTO.getRoomCount(); i++) {
      OrderDetail detail = new OrderDetail();
      BeanUtils.copyProperties(requestDTO, detail);
      detail.setOrderId(orderBaseId);
      detail.setUserId(requestDTO.getUserId());
      detail.setGuestName(requestDTO.getGuestNames().get(i));
      detail.setRoomNumber(roomList.get(i).getRoomNumber());
      detail.setPrice(requestDTO.getRoomTotalPrice());
      orderDetailMapper.insertSelective(detail);
    }
  }

  @Override
  public List<OrderDetail> getOrderDetailByOrderId(List<Long> orderIds) {
    OrderDetailExample example = new OrderDetailExample();
    OrderDetailExample.Criteria criteria = example.createCriteria();
    criteria.andOrderIdIn(orderIds).andIsDeletedEqualTo((byte) 0);
    List<OrderDetail> list = orderDetailMapper.selectByExample(example);
    return list;
  }

  @Override
  public List<OrderInfoListResponse> getOrderListInfo(List<Long> orderIds, String userId) {

    return orderAndDetailMapperExt.getOrderListInfo(userId, orderIds);
  }

  @Override
  public void dealChangeRoom(ChangeRoomRequestDTO requestDTO, String userId,
      LocalDate endDate) throws BizException {
    //query order detail info
    OrderDetail detail = orderDetailMapper.selectByPrimaryKey(requestDTO.getOrderDetailId());
    if (detail==null){
      throw new BizException(ResponseCode.pay_error);
    }
    //update original detail info
    detail.setStatus((byte)2);//changed

    detail.setChangeDate(LocalDate.now());
    orderDetailMapper.updateByPrimaryKeySelective(detail);
    //insert difference detail
    OrderDetail newDetail = new OrderDetail();
    newDetail.setOrderId(requestDTO.getOrderId());
    newDetail.setOriginalDetailId(requestDTO.getOrderDetailId());
    newDetail.setUserId(userId);
    if (requestDTO.getRealDiffPrice().doubleValue()==0){
      newDetail.setChangeType(ChangeRoomTypeEnum.CHANGE.getCode());//change room
    }else{
      newDetail.setChangeType(ChangeRoomTypeEnum.UPDATE.getCode());//update room
    }
    newDetail.setGuestName(detail.getGuestName());
    newDetail.setPriceDifference(requestDTO.getRealDiffPrice());
    newDetail.setRoomTypeId(requestDTO.getRoomTypeId());
    List<DistributableRoomDTO> roomList = orderAndDetailMapperExt
        .getDistributableRoomList(requestDTO.getRoomTypeId(), requestDTO.getHotelId(),
            LocalDate.now(), endDate);
    newDetail.setRoomNumber(roomList.get(0).getRoomNumber());
    orderDetailMapper.insertSelective(newDetail);
  }
}
