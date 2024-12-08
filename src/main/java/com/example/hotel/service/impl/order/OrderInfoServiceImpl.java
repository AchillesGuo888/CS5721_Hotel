package com.example.hotel.service.impl.order;

import cn.hutool.core.util.ObjectUtil;
import com.example.hotel.common.base.ResponseCode;
import com.example.hotel.dto.AvailableRoomCountDTO;
import com.example.hotel.dto.request.BookRoomRequestDTO;
import com.example.hotel.dto.request.PrebookRoomRequestDTO;
import com.example.hotel.dto.response.PreBookRoomResponse;
import com.example.hotel.dto.response.PriceResponse;
import com.example.hotel.entity.HotelInfo;
import com.example.hotel.entity.OrderBase;
import com.example.hotel.entity.User;
import com.example.hotel.exception.BizException;
import com.example.hotel.exception.NoRollbackException;
import com.example.hotel.mapper.HotelInfoMapper;
import com.example.hotel.mapper.OrderBaseMapper;
import com.example.hotel.mapper.ext.OrderAndDetailMapperExt;
import com.example.hotel.service.impl.factory.PriceFactory;
import com.example.hotel.service.order.OrderAndDetailService;
import com.example.hotel.service.order.OrderInfoService;
import com.example.hotel.service.price.PriceCalculationService;
import com.example.hotel.service.roomType.RoomTypePriceService;
import com.example.hotel.service.user.UserService;
import com.example.hotel.util.DateUtil;
import com.example.hotel.util.JwtUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.MapUtils;

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

  @Override
  @Transactional(rollbackFor = Exception.class, noRollbackFor = NoRollbackException.class)
  public Boolean bookRoom(BookRoomRequestDTO requestDTO, String token) throws BizException {

    //check room count for all guest
    PrebookRoomRequestDTO queryDTO = new PrebookRoomRequestDTO();
    BeanUtils.copyProperties(requestDTO,queryDTO);
    List<AvailableRoomCountDTO> roomTypeInfoList = getRoomTypeList(queryDTO);
    Map<Long, BigDecimal> roomTypePriceMap = getRoomTypePrice(queryDTO);

    //query user info
    String userId = jwtUtil.getUserIdFromToken(token);
    User userInfo = userService.findUserByUserId(userId);
    //check price whether price have changed
    BigDecimal originalPrice = roomTypePriceMap.get(requestDTO.getRoomTypeId());
    if (originalPrice.compareTo(requestDTO.getRoomPrice())!=0){
      throw new BizException(ResponseCode.room_price_change);
    }
    // create order info
    Long orderBaseId = createOrderInfo(requestDTO,userInfo);
    // create order detail info
    requestDTO.setUserId(userId);
    orderAndDetailService.addOrderDetail(orderBaseId,requestDTO);


    return true;
  }

  private Long createOrderInfo(BookRoomRequestDTO requestDTO,
      User userInfo) {
    OrderBase orderBase = new OrderBase();
    BeanUtils.copyProperties(requestDTO,orderBase);
    orderBase.setOperator(userInfo.getUserId());
    orderBaseMapper.insertSelective(orderBase);
    return orderBase.getId();
  }

  @Override
  public PreBookRoomResponse pregenerateOrder(PrebookRoomRequestDTO requestDTO, String token)
      throws BizException {
    PreBookRoomResponse result = PreBookRoomResponse.builder().build();

    //query hotel info
    assembleHotelInfo(result,requestDTO);
    //query room type info and original price
    assembleRoomTypeInfo(result,requestDTO);
    //query user info
    String userId = jwtUtil.getUserIdFromToken(token);
    User userInfo = userService.findUserByUserId(userId);
    //calculate the discount and price
    calculatePrice(requestDTO,result,userInfo);
    return result;
  }

  private void calculatePrice(PrebookRoomRequestDTO requestDTO, PreBookRoomResponse result, User userInfo)
      throws BizException {

    PriceCalculationService priceCalculationService = PriceFactory.getService(userInfo.getMemberShip());
    PriceResponse preBookPrice = priceCalculationService.calculateOrderPrice(requestDTO,result.getRoomTypePrice(),userInfo.getUserId());
    result.setEarnPointsCount(preBookPrice.getEarnPointsCount());
    result.setMembershipDiscount(preBookPrice.getMembershipDiscount());
    result.setRealPrice(preBookPrice.getRealPrice());
  }

  private void assembleRoomTypeInfo(PreBookRoomResponse result, PrebookRoomRequestDTO requestDTO)
      throws BizException {
    List<AvailableRoomCountDTO> roomTypeInfoList = getRoomTypeList(requestDTO);
    Map<Long, BigDecimal> roomTypePriceMap = getRoomTypePrice(requestDTO);

    result.setRoomTypeId(requestDTO.getRoomTypeId());
    result.setRoomTypeName(roomTypeInfoList.get(0).getRoomTypeName());
    result.setRoomTypePrice(roomTypePriceMap.get(requestDTO.getRoomTypeId()));
  }

  private List<AvailableRoomCountDTO> getRoomTypeList(
      PrebookRoomRequestDTO requestDTO) throws BizException {
    List<Long> hotelIds = Arrays.asList(requestDTO.getHotelId());
    List<Long> roomTypeIds = Arrays.asList(requestDTO.getRoomTypeId());
    List<AvailableRoomCountDTO> roomTypeInfoList =
        orderAndDetailService.queryAvailableRoomType(hotelIds,
            requestDTO.getStartDate(),
            requestDTO.getEndDate(),null,roomTypeIds);
    if (CollectionUtils.isEmpty(roomTypeInfoList)){
      throw new BizException(ResponseCode.room_type_not_exists);
    }
    if (roomTypeInfoList.get(0).getAvailableCount()<requestDTO.getRoomCount()){
      throw new BizException(ResponseCode.room_count_error);
    }
    return roomTypeInfoList;
  }

  private Map<Long, BigDecimal> getRoomTypePrice(
      PrebookRoomRequestDTO requestDTO) throws BizException {
    List<Long> roomTypeIds = Arrays.asList(requestDTO.getRoomTypeId());
    Map<Long, BigDecimal> roomTypePriceMap = roomTypePriceService.getHotelsRoomTypeAndPrice(roomTypeIds,requestDTO.getStartDate(),requestDTO.getEndDate());
    if (MapUtils.isEmpty(roomTypePriceMap)){
      throw new BizException(ResponseCode.room_type_not_exists);
    }
    return roomTypePriceMap;
  }

  private void assembleHotelInfo(PreBookRoomResponse result, PrebookRoomRequestDTO requestDTO)
      throws BizException {
    HotelInfo hotelInfo = hotelInfoMapper.selectByPrimaryKey(requestDTO.getHotelId());
    if (ObjectUtil.isNull(hotelInfo)){
      throw new BizException(ResponseCode.hotel_not_exists);
    }
    result.setHotelId(hotelInfo.getId());
    result.setHotelName(hotelInfo.getHotelName());
  }
}
