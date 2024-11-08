package com.example.hotel.service.impl.user;

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
import com.example.hotel.entity.User;
import com.example.hotel.entity.UserExample;
import com.example.hotel.enums.MemberShipEnum;
import com.example.hotel.enums.UserTypeEnum;
import com.example.hotel.exception.BizException;
import com.example.hotel.exception.NoRollbackException;
import com.example.hotel.mapper.UserMapper;
import com.example.hotel.service.user.UserService;
import com.example.hotel.service.user.auth.AbstractAuth;
import com.example.hotel.service.user.auth.Auth4EmailPasswordMatch;
import com.example.hotel.util.EnumUtil;
import com.example.hotel.util.JwtUtil;
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
import com.example.hotel.util.Md5Util;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

  private final UserMapper userMapper;

  private final JwtUtil jwtUtil;

  private final VerificationCodeUtil verificationCodeUtil;

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
     * 3.get token after sign-up
     */

    String token = JwtUtil.generateToken(newUserId, UserTypeEnum.USER.getCode());
    return RegisterResponse.builder().accessToken(token).build();

  }

  @Override
  public RegisterResponse userLogin(UserLoginRequestDTO requestDTO) throws BizException {

    //get auth tool
    AbstractAuth auth = getAuth(requestDTO);
    //auth user info
    auth.auth();
    //create token and return result
    String token = JwtUtil.generateToken(auth.getCurrentUser().getUserId(), UserTypeEnum.USER.getCode());
    return RegisterResponse.builder().accessToken(token).build();
  }

  @Override
  public void userLogout(String token) {
    if (StringUtils.isNotEmpty(token)&&token.startsWith("Bearer ")){
      jwtUtil.blacklistToken(token.substring(7)); // put Token into blacklist
      SecurityContextHolder.clearContext();
    }

  }

  @Override
  @Transactional(rollbackFor = Exception.class, noRollbackFor = NoRollbackException.class)
  public UpdateInfoResponse modifyPassword(PasswordModifyRequestDTO requestDTO) throws BizException {
    /**
     * 1.check parameter
     */
    String email = requestDTO.getEmail();
    String newPassword = requestDTO.getNewPassword();
    String oldPassword = requestDTO.getOldPassword();
    if (StrUtil.isBlank(email)) {
      throw new BizException(ResponseCode.email_error_rules);
    }
    if (StrUtil.isBlank(oldPassword)) {
      throw new BizException(ResponseCode.password_old_error_rules);
    }
    if (StrUtil.isBlank(newPassword)) {
      throw new BizException(ResponseCode.password_new_error_rules);
    }
    if (newPassword.equals(oldPassword)) {
      throw new BizException(ResponseCode.password_old_new_same);
    }

    // query user info
    User user = findUserByEmail(requestDTO.getEmail());
    if (user == null) {
      throw new BizException(ResponseCode.email_not_exist);
    }

    /**
     * 2.check old password
     */
    String userId = user.getUserId();
    String salt = user.getSalt();
    String passwordInDb = user.getPassword();
    String oldPasswordInDb = Md5Util.getSaltMd5AndSha(oldPassword, salt);
    if (!passwordInDb.equals(oldPasswordInDb)) {
      throw new BizException(ResponseCode.password_old_not_correct);
    }

    // modify password
    User update = new User();
    update.setUserId(userId);
    String newPasswordInDb = Md5Util.getSaltMd5AndSha(newPassword, salt);
    update.setPassword(newPasswordInDb);

    //update DB
    UserExample example = new UserExample();
    UserExample.Criteria criteria = example.createCriteria();
    criteria.andUserIdEqualTo(userId);
    int count = userMapper.updateByExampleSelective(update, example);
    if (count == 0) {
      throw new BizException(ResponseCode.server_err);
    }
    return UpdateInfoResponse.builder().result(true).build();
  }

  @Override
  @Transactional(rollbackFor = Exception.class, noRollbackFor = NoRollbackException.class)

  public UpdateInfoResponse forgetPassword(ForgetPasswordRequestDTO requestDTO)
      throws BizException {
    User user = findUserByEmail(requestDTO.getEmail());
    if (user==null){
      throw new BizException(ResponseCode.email_not_exist);
    }
    if (verificationCodeUtil.verifyCode(requestDTO.getCode(),requestDTO.getEmail())){
      throw new BizException(ResponseCode.code_false);
    }

    if (!requestDTO.getNewPassword().equals(requestDTO.getConfirmPassword())) {
      throw new BizException(ResponseCode.password_not_same);
    }
    if (!ValidateUtils.checkPassword(requestDTO.getNewPassword())) {
      throw new BizException(ResponseCode.password_error_rules);
    }

    // get md5 password
    String userId = user.getUserId();
    String newPasswordInDb = createPassWordInDB(user.getSalt(), requestDTO.getNewPassword());

    User update = new User();
    update.setPassword(newPasswordInDb);

    // update new password
    UserExample example = new UserExample();
    UserExample.Criteria criteria = example.createCriteria();
    criteria.andUserIdEqualTo(userId);
    int count = userMapper.updateByExampleSelective(update, example);

    if (count == 0) {
      throw new BizException(ResponseCode.server_err);
    }
    return UpdateInfoResponse.builder().result(true).build();
  }

  @Override
  public UserInfoResponse getUserInfo(String token) throws BizException {
    String userId = jwtUtil.getUserIdFromToken(token);
    if (StringUtils.isEmpty(userId)){
      throw new BizException(ResponseCode.token_error);
    }

    User user = findUserByUserId(userId);

    UserInfoResponse response = UserInfoResponse.builder().build();
    BeanUtils.copyProperties(user,response);
    response.setMemberShipDesc(EnumUtil.getEnumDesc(user.getMemberShip(),MemberShipEnum.class));

    return response;
  }

  @Override
  public UpdateInfoResponse updateUserInfo(ModifyUserInfoRequestDTO requestDTO, String token)
      throws BizException {
    String userId = jwtUtil.getUserIdFromToken(token);
    if (StringUtils.isEmpty(userId)){
      throw new BizException(ResponseCode.token_error);
    }
    User user = findUserByUserId(userId);

    if (!user.getEmail().equals(requestDTO.getEmail())){
      //check new email
      User existUser = findUserByEmail(requestDTO.getEmail());
        if (ObjectUtil.isNotNull(existUser)){
          throw new BizException(ResponseCode.email_has_exist);
        }
    }
    BeanUtils.copyProperties(requestDTO,user);
    UserExample example = new UserExample();
    UserExample.Criteria criteria = example.createCriteria();
    criteria.andIdEqualTo(user.getId());
    userMapper.updateByExample(user,example);


    return UpdateInfoResponse.builder().result(true).build();
  }

  /**
   * get user md5 password
   *
   * @param salt
   * @param password
   * @return
   */
  private String createPassWordInDB(String salt, String password) throws BizException {
    return Md5Util.getSaltMd5AndSha(password, salt);
  }

  /**
   * create new user
   *
   * @return
   */

  @Transactional(rollbackFor = Exception.class, noRollbackFor = NoRollbackException.class)
  public String insertNewUser(RegisterRequestDTO requestDTO) {
    /**
     * 2.create new user
     */
    // create Salt
    String salt = Md5Util.createSalt();

    // create user
    User newUser = new User();
    BeanUtils.copyProperties(requestDTO, newUser);
    // store salt secret in DB
    String passwordInDb = Md5Util.getSaltMd5AndSha(requestDTO.getEmail(), salt);
    Long newUserId = Md5Util.createNewUserId();
    newUser.setUserId(newUserId.toString());
    newUser.setPassword(passwordInDb);
    newUser.setSalt(salt);
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

  /**
   * get auth class
   *
   * @param request
   * @return
   */
  private AbstractAuth getAuth(UserLoginRequestDTO request) throws BizException {
    User user = null;
    AbstractAuth auth = null;
    //邮箱密码登录
      user = findUserByEmail(request.getEmail());
      String salt = user.getSalt();
    return new Auth4EmailPasswordMatch(user, request.getPassword(), salt);

  }

  /**
   * get user info by email
   *
   * @param email
   * @return
   */
  private User findUserByEmail(String email) throws BizException {
    UserExample example = new UserExample();
    UserExample.Criteria criteria = example.createCriteria();
    criteria.andEmailEqualTo(email);
    List<User> userList = userMapper.selectByExample(example);
    if (CollectionUtils.isEmpty(userList)) {
      throw new BizException(ResponseCode.email_not_exist);
    }
    return userList.get(0);
  }

  /**
   * get user info by userId
   *
   * @param userId
   * @return
   */
  private User findUserByUserId(String userId) throws BizException {
    UserExample example = new UserExample();
    UserExample.Criteria criteria = example.createCriteria();
    criteria.andUserIdEqualTo(userId);
    List<User> userList = userMapper.selectByExample(example);
    if (CollectionUtils.isEmpty(userList)) {
      throw new BizException(ResponseCode.email_not_exist);
    }
    return userList.get(0);
  }


}
