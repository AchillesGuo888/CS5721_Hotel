package com.example.hotel.service.impl.point;

import com.example.hotel.common.constant.CommonConstant;
import com.example.hotel.entity.OrderBase;
import com.example.hotel.entity.User;
import com.example.hotel.entity.UserExample;
import com.example.hotel.entity.UserPoints;
import com.example.hotel.enums.MemberShipEnum;
import com.example.hotel.exception.NoRollbackException;
import com.example.hotel.mapper.UserMapper;
import com.example.hotel.mapper.ext.BatchInsertMapperExt;
import com.example.hotel.mapper.ext.UserPointsMapperExt;
import com.example.hotel.service.impl.factory.PriceFactory;
import com.example.hotel.service.point.UserPointService;
import com.example.hotel.service.price.PriceCalculationService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class UserPointServiceImpl implements UserPointService {

  private final UserMapper userMapper;
  private final BatchInsertMapperExt batchInsertMapperExt;
  private final UserPointsMapperExt userPointsMapperExt;

  @Override
  @Transactional(rollbackFor = Exception.class, noRollbackFor = NoRollbackException.class)
  public void batchInsertUserPoint(List<OrderBase> orderBaseList) {
    List<UserPoints> userPointsList = new ArrayList<>();
    //get Map<userId,level>
    List<String> userIds = orderBaseList.stream().map(x -> x.getUserId())
        .collect(Collectors.toList());
    Map<String, Byte> userLevelMap = getUserLevel(userIds);
    PriceCalculationService priceCalculationService;

    for (OrderBase item : orderBaseList) {
      UserPoints pointInfo = new UserPoints();
      pointInfo.setOrderId(item.getId());
      pointInfo.setUserId(item.getUserId());
      pointInfo.setExpense(item.getRealPrice());
      priceCalculationService = PriceFactory.getService(userLevelMap.get(item.getUserId()));
      Integer points = priceCalculationService.getEarnPoints(item.getRealPrice());
      pointInfo.setPoints(points);
      userPointsList.add(pointInfo);
    }
    //batch user points addition
    batchInsertMapperExt.batchInsertUserPoints(userPointsList);

  }

  @Override
  @Transactional(rollbackFor = Exception.class, noRollbackFor = NoRollbackException.class)
  public void updateUserLevel(List<String> userIds) {
    //calculate points of the user of each order
    Map<String, Byte> userLevelMap = getUserLevel(userIds);
    List<UserPoints> userPointSumPointList = userPointsMapperExt.getUserPointSum(userIds);
    updateLevel(userPointSumPointList, userLevelMap);
  }

  private void updateLevel(List<UserPoints> userPointSumPointList, Map<String, Byte> userLevelMap) {
    for (UserPoints item : userPointSumPointList) {
      Byte userLevel = checkUserLevel(item.getPoints());
      if (userLevel.equals(userLevelMap.get(item.getUserId()))) {
        //If the level doesn't change
        continue;
      }
      User updateParam = new User();
      updateParam.setUserId(item.getUserId());
      updateParam.setMemberShip(userLevel);
      UserExample example = new UserExample();
      UserExample.Criteria criteria = example.createCriteria();
      criteria.andUserIdEqualTo(item.getUserId());
      userMapper.updateByExampleSelective(updateParam, example);
    }
  }

  private Byte checkUserLevel(Integer points) {
    if (points < CommonConstant.SILVER_MEMBER_POINTS) {
      return MemberShipEnum.COOPER.getCode();
    } else if (points < CommonConstant.GOLD_MEMBER_POINTS) {
      return MemberShipEnum.SILVER.getCode();
    } else if (points < CommonConstant.DIAMOND_MEMBER_POINTS) {
      return MemberShipEnum.GOLD.getCode();
    } else {
      return MemberShipEnum.DIAMOND.getCode();
    }
  }

  private Map<String, Byte> getUserLevel(List<String> userIds) {
    UserExample example = new UserExample();
    UserExample.Criteria criteria = example.createCriteria();
    criteria.andUserIdIn(userIds).andIsDeletedEqualTo((byte) 0);
    List<User> users = userMapper.selectByExample(example);
    return users.stream().collect(Collectors.toMap(User::getUserId, User::getMemberShip));
  }
}
