package com.example.hotel.service.impl.order;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.example.hotel.common.base.ResponseCode;
import com.example.hotel.dto.AvailableRoomCountDTO;
import com.example.hotel.dto.request.BookRoomRequestDTO;
import com.example.hotel.dto.request.ModifyOrderInfoRequestDTO;
import com.example.hotel.dto.request.PrebookRoomRequestDTO;
import com.example.hotel.dto.request.QueryOrderAmountRequestDTO;
import com.example.hotel.dto.request.QueryOrderDetailRequestDTO;
import com.example.hotel.dto.response.ChangeOrderRoomCountResponse;
import com.example.hotel.dto.response.OrderInfoListResponse;
import com.example.hotel.dto.response.OrderInfoResponse;
import com.example.hotel.dto.response.PreBookRoomResponse;
import com.example.hotel.dto.response.PriceResponse;
import com.example.hotel.entity.HotelInfo;
import com.example.hotel.entity.OrderBase;
import com.example.hotel.entity.OrderBaseExample;
import com.example.hotel.entity.OrderDetail;
import com.example.hotel.entity.User;
import com.example.hotel.enums.OrderStatusEnum;
import com.example.hotel.exception.BizException;
import com.example.hotel.exception.NoRollbackException;
import com.example.hotel.mapper.HotelInfoMapper;
import com.example.hotel.mapper.OrderBaseMapper;
import com.example.hotel.mapper.OrderDetailMapper;
import com.example.hotel.service.command.ChangeRoomCountCommand;
import com.example.hotel.service.impl.factory.PriceFactory;
import com.example.hotel.service.order.OrderAndDetailService;
import com.example.hotel.service.order.OrderInfoService;
import com.example.hotel.service.point.UserPointService;
import com.example.hotel.service.price.PriceCalculationService;
import com.example.hotel.service.roomType.RoomTypePriceService;
import com.example.hotel.service.user.UserService;
import com.example.hotel.util.DateUtil;
import com.example.hotel.util.JwtUtil;
import com.example.hotel.util.Md5Util;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageSerializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.MapUtils;
import org.thymeleaf.util.StringUtils;

@Service
@AllArgsConstructor
@Slf4j
public class OrderInfoServiceImpl implements OrderInfoService {

  private final JwtUtil jwtUtil;
  private final OrderAndDetailService orderAndDetailService;
  private final HotelInfoMapper hotelInfoMapper;
  private final RoomTypePriceService roomTypePriceService;
  private final UserService userService;
  private final OrderBaseMapper orderBaseMapper;
  private final UserPointService userPointService;

  @Override
  @Transactional(rollbackFor = Exception.class, noRollbackFor = NoRollbackException.class)
  public Boolean bookRoom(BookRoomRequestDTO requestDTO, String token) throws BizException {

    //check room count for all guest
    PrebookRoomRequestDTO queryDTO = new PrebookRoomRequestDTO();
    BeanUtils.copyProperties(requestDTO, queryDTO);
    List<AvailableRoomCountDTO> roomTypeInfoList = getRoomTypeList(queryDTO);
    Map<Long, BigDecimal> roomTypePriceMap = getRoomTypePrice(queryDTO);

    //query user info
    String userId = jwtUtil.getUserIdFromToken(token);
    User userInfo = userService.findUserByUserId(userId);
    //check price whether price have changed
    BigDecimal currentRoomPrice = roomTypePriceMap.get(requestDTO.getRoomTypeId());
    BigDecimal dates = new BigDecimal(
        DateUtil.getBetweenDays(requestDTO.getStartDate(), requestDTO.getEndDate()));
    BigDecimal orderRoomPrice = currentRoomPrice.multiply(dates).setScale(2, RoundingMode.HALF_UP);
    if (orderRoomPrice.compareTo(requestDTO.getRoomPrice()) != 0) {
      throw new BizException(ResponseCode.room_price_change);
    }
    // create order info
    Long orderBaseId = createOrderInfo(requestDTO, userInfo);
    // create order detail info
    requestDTO.setUserId(userId);
    orderAndDetailService.addOrderDetail(orderBaseId, requestDTO);

    return true;
  }

  private Long createOrderInfo(BookRoomRequestDTO requestDTO,
      User userInfo) {
    OrderBase orderBase = new OrderBase();
    BeanUtils.copyProperties(requestDTO, orderBase);
    orderBase.setUserId(userInfo.getUserId());
    orderBase.setOperator(userInfo.getUserId());
    orderBase.setOrderNumber(Md5Util.getRandom(10));
    orderBaseMapper.insertSelective(orderBase);
    return orderBase.getId();
  }

  @Override
  public PreBookRoomResponse pregenerateOrder(PrebookRoomRequestDTO requestDTO, String token)
      throws BizException {

    //query hotel info
    HotelInfo hotelInfo = getHotelInfo(requestDTO);
    //query room type info and original price
    Map<Long, BigDecimal> roomTypePriceMap = getRoomTypePrice(requestDTO);
    AvailableRoomCountDTO roomTypeInfo = CollectionUtil.getFirst(getRoomTypeList(requestDTO));
    //query user info
    String userId = jwtUtil.getUserIdFromToken(token);
    User userInfo = userService.findUserByUserId(userId);
    //calculate the discount and price
    QueryOrderAmountRequestDTO calculateRequest = new QueryOrderAmountRequestDTO();
    BeanUtils.copyProperties(requestDTO, calculateRequest);
    calculateRequest.setRoomTypePrice(roomTypePriceMap.get(requestDTO.getRoomTypeId()));
    PriceResponse preBookPrice = calculatePrice(calculateRequest, userInfo);

    return assemblePreBookInfo(hotelInfo, roomTypePriceMap, userInfo, preBookPrice, requestDTO,
        roomTypeInfo);
  }

  @Override
  public ChangeOrderRoomCountResponse removeOrderRoom(PrebookRoomRequestDTO requestDTO,
      String token) throws BizException {
    ChangeRoomReceiver receiver = new ChangeRoomReceiver();
    ChangeRoomCountCommand command = new RemoveRoomCommand(receiver, requestDTO.getRoomCount());
    Integer roomCount = command.execute();
    requestDTO.setRoomCount(roomCount);
    ChangeOrderRoomCountResponse result = getOrderPrice(requestDTO, token);
    return result;
  }

  private ChangeOrderRoomCountResponse getOrderPrice(PrebookRoomRequestDTO requestDTO, String token)
      throws BizException {
    ChangeOrderRoomCountResponse result = ChangeOrderRoomCountResponse.builder().build();
    Map<Long, BigDecimal> roomTypePriceMap = getRoomTypePrice(requestDTO);
    //query user info
    String userId = jwtUtil.getUserIdFromToken(token);
    User userInfo = userService.findUserByUserId(userId);
    //calculate the discount and price
    QueryOrderAmountRequestDTO calculateRequest = new QueryOrderAmountRequestDTO();
    BeanUtils.copyProperties(requestDTO, calculateRequest);
    calculateRequest.setRoomTypePrice(roomTypePriceMap.get(requestDTO.getRoomTypeId()));
    PriceResponse preBookPrice = calculatePrice(calculateRequest, userInfo);
    BeanUtils.copyProperties(preBookPrice, result);
    return result;
  }

  @Override
  public ChangeOrderRoomCountResponse addOrderRoom(PrebookRoomRequestDTO requestDTO, String token)
      throws BizException {
    ChangeRoomReceiver receiver = new ChangeRoomReceiver();
    ChangeRoomCountCommand command = new AddRoomCommand(receiver, requestDTO.getRoomCount());
    Integer roomCount = command.execute();
    requestDTO.setRoomCount(roomCount);
    ChangeOrderRoomCountResponse result = getOrderPrice(requestDTO, token);
    return result;
  }

  @Override
  public OrderInfoResponse queryOrderDetail(Long id) throws BizException {
    //get order base info
    OrderBase orderBase = orderBaseMapper.selectByPrimaryKey(id);
    if (ObjectUtil.isNull(orderBase)) {
      throw new BizException(ResponseCode.order_error);
    }
    //replace querying info by decorator
    //get order detail info
    List<OrderDetail> orderDetailList = orderAndDetailService
        .getOrderDetailByOrderId(Arrays.asList(id));
    OrderInfoResponse result = getOrderDetailInfo(orderBase, orderDetailList);
//    OrderInfoResponse result = OrderInfoResponse.builder().build();
//    BeanUtils.copyProperties(orderBase,result);
    return result;
  }

  @Override
  public Boolean modifyOrder(ModifyOrderInfoRequestDTO requestDTO) throws BizException {
    if (ObjectUtil.isNull(requestDTO) || StringUtils.isEmpty(requestDTO.getContactName())
        || StringUtils.isEmpty(requestDTO.getContactPhone())) {
      throw new BizException(ResponseCode.param_error);
    }
    return null;
  }

  @Override
  public PageSerializable<OrderInfoListResponse> queryOrderList(
      QueryOrderDetailRequestDTO requestDTO, String token) throws BizException {
    if (requestDTO.getPage() == null || requestDTO.getPageSize() == null) {
      return new PageSerializable<>();
    }

    String userId = jwtUtil.getUserIdFromToken(token);
    OrderBaseExample example = new OrderBaseExample();
    OrderBaseExample.Criteria criteria = example.createCriteria();
    criteria.andOperatorEqualTo(userId)
        .andIsDeletedEqualTo((byte) 0)
        .andIsCancelledEqualTo((byte) 0);
    List<OrderBase> orderBaseList = orderBaseMapper.selectByExample(example);
    PageHelper.startPage(requestDTO.getPage(), requestDTO.getPageSize());

    if (CollectionUtils.isEmpty(orderBaseList)) {
      return new PageSerializable<>();
    }
    PageSerializable<OrderBase> pageInfos = new PageSerializable<>(orderBaseList);
    List<Long> orderIds = pageInfos.getList().stream().map(x -> x.getId())
        .collect(Collectors.toList());

    List<OrderInfoListResponse> resultList = getOrderAndRoomTypeName(orderIds, userId);

    PageSerializable<OrderInfoListResponse> resultPage = new PageSerializable<>();
    resultPage.setList(resultList);
    resultPage.setTotal(pageInfos.getTotal());
    return resultPage;
  }

  @Override
  public void scheduleFinishOrder() {
    //get all orders, which finish today
    OrderBaseExample example = new OrderBaseExample();
    OrderBaseExample.Criteria criteria = example.createCriteria();
    criteria.andIsDeletedEqualTo((byte) 0)
        .andStatusEqualTo(OrderStatusEnum.CHECK_IN.getCode())
        .andIsCancelledEqualTo((byte) 0).andEndDateEqualTo(LocalDate.now());
    List<OrderBase> orderBaseList = orderBaseMapper.selectByExample(example);
    if (CollectionUtils.isNotEmpty(orderBaseList)) {
      //add user points
      userPointService.batchInsertUserPoint(orderBaseList);
      //update user membership level
      List<String> userIds = orderBaseList.stream().map(x -> x.getUserId())
          .collect(Collectors.toList());
      userPointService.updateUserLevel(userIds);
      //update order statue to finished
      updateFinishOrder(orderBaseList);

    }
  }

  private void updateFinishOrder(List<OrderBase> orderBaseList) {
    List<Long> ids = orderBaseList.stream().map(x -> x.getId()).collect(Collectors.toList());
    OrderBase param = new OrderBase();
    param.setStatus(OrderStatusEnum.FINISHED.getCode());
    OrderBaseExample updateExample = new OrderBaseExample();
    OrderBaseExample.Criteria updateCriteria = updateExample.createCriteria();
    updateCriteria.andIdIn(ids);
    orderBaseMapper.updateByExampleSelective(param, updateExample);
  }


  private List<OrderInfoListResponse> getOrderAndRoomTypeName(List<Long> orderIds, String userId) {
    List<OrderInfoListResponse> list = orderAndDetailService.getOrderListInfo(orderIds, userId);
    Map<Long, OrderInfoListResponse> orderRoomTypeMap = new HashMap<>();
    for (OrderInfoListResponse item : list) {
      if (ObjectUtil.isNull(orderRoomTypeMap.get(item.getOrderId()))) {
        orderRoomTypeMap.put(item.getOrderId(), item);
      } else {
        String roomTypeName = orderRoomTypeMap.get(item.getOrderId()).getRoomTypeName();
        if (!roomTypeName.contains(item.getRoomTypeName())) {
          roomTypeName = String.join(",", Arrays.asList(roomTypeName, item.getRoomTypeName()));
          orderRoomTypeMap.get(item.getOrderId()).setRoomTypeName(roomTypeName);
        }
      }
    }

    List<OrderInfoListResponse> resultList = new ArrayList<>();
    orderRoomTypeMap.entrySet().stream().forEach(entry -> {
      resultList.add(entry.getValue());
    });
    return resultList;
  }

  private OrderInfoResponse getOrderDetailInfo(OrderBase orderBase,
      List<OrderDetail> orderDetailList) {
    OrderInfoResponse response = OrderInfoResponse.builder().build();
    BeanUtils.copyProperties(orderBase, response);

    return response;
  }

  private PriceResponse calculatePrice(QueryOrderAmountRequestDTO requestDTO, User userInfo)
      throws BizException {

    PriceCalculationService priceCalculationService = PriceFactory
        .getService(userInfo.getMemberShip());
    PriceResponse preBookPrice = priceCalculationService.calculateOrderPrice(requestDTO);
    return preBookPrice;
  }

  private List<AvailableRoomCountDTO> getRoomTypeList(
      PrebookRoomRequestDTO requestDTO) throws BizException {
    List<Long> hotelIds = Arrays.asList(requestDTO.getHotelId());
    List<Long> roomTypeIds = Arrays.asList(requestDTO.getRoomTypeId());
    List<AvailableRoomCountDTO> roomTypeInfoList =
        orderAndDetailService.queryAvailableRoomType(hotelIds,
            requestDTO.getStartDate(),
            requestDTO.getEndDate(), null, roomTypeIds);
    if (CollectionUtils.isEmpty(roomTypeInfoList)) {
      throw new BizException(ResponseCode.room_type_not_exists);
    }
    if (roomTypeInfoList.get(0).getAvailableCount() < requestDTO.getRoomCount()) {
      throw new BizException(ResponseCode.room_count_error);
    }
    return roomTypeInfoList;
  }

  private Map<Long, BigDecimal> getRoomTypePrice(
      PrebookRoomRequestDTO requestDTO) throws BizException {
    List<Long> roomTypeIds = Arrays.asList(requestDTO.getRoomTypeId());
    Map<Long, BigDecimal> roomTypePriceMap = roomTypePriceService
        .getHotelsRoomTypeAndPrice(roomTypeIds, requestDTO.getStartDate(), requestDTO.getEndDate());
    if (MapUtils.isEmpty(roomTypePriceMap)) {
      throw new BizException(ResponseCode.room_type_not_exists);
    }
    return roomTypePriceMap;
  }

  private HotelInfo getHotelInfo(PrebookRoomRequestDTO requestDTO)
      throws BizException {
    HotelInfo hotelInfo = hotelInfoMapper.selectByPrimaryKey(requestDTO.getHotelId());
    if (ObjectUtil.isNull(hotelInfo)) {
      throw new BizException(ResponseCode.hotel_not_exists);
    }
    return hotelInfo;

  }

  private PreBookRoomResponse assemblePreBookInfo(HotelInfo hotelInfo,
      Map<Long, BigDecimal> roomTypePriceMap, User userInfo, PriceResponse preBookPrice,
      PrebookRoomRequestDTO requestDTO, AvailableRoomCountDTO roomTypeInfo) {
    PreBookRoomResponse result = PreBookRoomResponse.builder().build();
    BeanUtils.copyProperties(requestDTO, result);
    BeanUtils.copyProperties(hotelInfo, result);
    BeanUtils.copyProperties(userInfo, result);
    BeanUtils.copyProperties(preBookPrice, result);
    result.setRoomTypeName(roomTypeInfo.getRoomTypeName());
    result.setRoomTypePrice(roomTypePriceMap.get(requestDTO.getRoomTypeId()));

    return result;
  }


  @Autowired
  private OrderDetailMapper orderDetailMapper;

  @Override
  public void cancelOrderById(Long orderId) {
    // 更新 order_base 表中的状态
    orderBaseMapper.updateOrderStatusToCancelled(orderId);

    // 更新 order_detail 表中的状态
    orderDetailMapper.updateRoomStatusByOrderId(orderId,3);
  }

  @Override
  public void cancelRoomsInOrder(Long orderId, List<Integer> roomNumbers) {
    // 更新指定房间的状态
    orderDetailMapper.updateRoomStatusByRoomNumbers(orderId, roomNumbers,3);

    // 检查订单中是否所有房间都被取消，如果是，则更新 order_base 状态
    int remainingRooms = orderDetailMapper.countActiveRoomsByOrderId(orderId);
    if (remainingRooms == 0) {
      orderBaseMapper.updateOrderStatusToCancelled(orderId);
    }
  }
}
