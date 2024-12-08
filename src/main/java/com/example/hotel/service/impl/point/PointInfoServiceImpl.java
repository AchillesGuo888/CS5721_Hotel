package com.example.hotel.service.impl.point;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.example.hotel.common.base.ResponseCode;
import com.example.hotel.common.constant.CommonConstant;
import com.example.hotel.dto.request.ForgetPasswordRequestDTO;
import com.example.hotel.dto.request.ModifyUserInfoRequestDTO;
import com.example.hotel.dto.request.PasswordModifyRequestDTO;
import com.example.hotel.dto.request.RegisterRequestDTO;
import com.example.hotel.dto.request.UserLoginRequestDTO;
import com.example.hotel.dto.response.RegisterResponse;
import com.example.hotel.dto.response.UpdateInfoResponse;
import com.example.hotel.dto.response.UserInfoResponse;
import com.example.hotel.entity.PointInfo;
import com.example.hotel.entity.PointInfoExample;
import com.example.hotel.entity.User;
import com.example.hotel.entity.UserExample;
import com.example.hotel.enums.MemberShipEnum;
import com.example.hotel.enums.UserTypeEnum;
import com.example.hotel.exception.BizException;
import com.example.hotel.exception.NoRollbackException;
import com.example.hotel.mapper.PointInfoMapper;
import com.example.hotel.mapper.UserMapper;
import com.example.hotel.service.point.PointInfoService;
import com.example.hotel.service.user.UserService;
import com.example.hotel.service.user.auth.AbstractAuth;
import com.example.hotel.service.user.auth.Auth4EmailPasswordMatch;
import com.example.hotel.util.EnumUtil;
import com.example.hotel.util.JwtUtil;
import com.example.hotel.util.Md5Util;
import com.example.hotel.util.ValidateUtils;
import com.example.hotel.util.VerificationCodeUtil;
import com.google.common.base.Strings;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class PointInfoServiceImpl implements PointInfoService {

  private final PointInfoMapper pointInfoMapper;

  @Override
  public Integer getUserPointCount(String userId) {
    PointInfoExample example = new PointInfoExample();
    PointInfoExample.Criteria criteria = example.createCriteria();
    criteria.andUserIdEqualTo(userId);
    List<PointInfo> pointInfoList = pointInfoMapper.selectByExample(example);
    Integer totalPoint = 0;
    for (PointInfo item: pointInfoList){
      totalPoint +=item.getPointCount();
    }
    return totalPoint;
  }
}
