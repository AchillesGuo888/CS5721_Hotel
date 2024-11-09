package com.example.hotel.service.impl.user;

import cn.hutool.core.util.StrUtil;
import com.example.hotel.common.base.ResponseCode;
import com.example.hotel.common.constant.CommonConstant;
import com.example.hotel.dto.request.RegisterRequestDTO;

import com.example.hotel.dto.response.RegisterResponse;
import com.example.hotel.entity.User;
import com.example.hotel.entity.UserExample;
import com.example.hotel.exception.BizException;
import com.example.hotel.exception.NoRollbackException;
import com.example.hotel.mapper.UserMapper;
import com.example.hotel.service.user.UserService;
import com.example.hotel.util.JwtUtil;
import com.example.hotel.util.ValidateUtils;
import com.google.common.base.Strings;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.hotel.util.MD5Util;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImplement implements UserService {

  private final UserMapper userMapper;

  private final static int saltSize = 16;

  @Override
  public RegisterResponse userSignUp(RegisterRequestDTO requestDTO)
      throws BizException {
    String code = requestDTO.getCode();
    String email = requestDTO.getEmail();
    String password = requestDTO.getPassword();


    /**
     * 1.check parameters
     */
    //check: parameters formation
    checkParameters(code, email, password);
    //check: whether the email exists or not
    isUserExistByEmail(email);


    // TODO check verification
    //String rightCode = redisUtils.get(CacheKeys.getEmailCodeKey(requestDTO.getEmail(), CodeScene.REGISTER.getCode()));

    String newUserId = insertNewUser(requestDTO);
    /**
     * 3.注册成功获取token
     */

    String token = JwtUtil.generateToken(newUserId, 0);
    return RegisterResponse.builder().accessToken(token).build();

  }

  /**
   * create new user
   *
   * @return
   */

  @Transactional(rollbackFor = Exception.class, noRollbackFor = NoRollbackException.class)
  public String insertNewUser(RegisterRequestDTO requestDTO) {
    /**
     * 2.插入用户信息
     */
    // 创建Salt
    String salt = MD5Util.createSalt();

    // 创建用户
    User newUser = new User();
    BeanUtils.copyProperties(requestDTO, newUser);
    // DB中存的加密后密码
    String passwordInDb = MD5Util.getSaltMd5AndSha(requestDTO.getEmail(), salt);
    Long newUserId = MD5Util.createNewUserId();
    newUser.setUserId(newUserId.toString());
    newUser.setPassword(passwordInDb);

    userMapper.insertSelective(newUser);

    return newUserId.toString();
  }


  private void checkParameters(String code, String email, String password) throws BizException {
    if (Strings.isNullOrEmpty(email) || !email.contains(CommonConstant.EMAIL)) {
      throw new BizException(ResponseCode.email_error_rules);
    }
    if (StrUtil.isBlank(code)) {
      throw new BizException(ResponseCode.code_false);
    }
    if (StrUtil.isBlank(password) || !ValidateUtils.checkPassword(password)) {
      throw new BizException(ResponseCode.password_error_rules);
    }
  }

  /**
   * check email exist or not
   *
   * @param email
   * @return
   */
  private void isUserExistByEmail(String email) throws BizException {
    UserExample example = new UserExample();
    UserExample.Criteria criteria = example.createCriteria();
    criteria.andEmailEqualTo(email);

    List<User> userList = userMapper.selectByExample(example);
    if (CollectionUtils.isNotEmpty(userList)){
      throw new BizException(ResponseCode.email_has_exist);
    }
  }
}
