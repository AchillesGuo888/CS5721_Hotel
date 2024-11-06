package com.example.hotel.controller;

import com.example.hotel.common.base.ResponseResult;
import com.example.hotel.dto.response.RegisterResponse;
import com.example.hotel.dto.request.*;
import com.example.hotel.dto.response.UpdateInfoResponse;
import com.example.hotel.exception.BizException;
import com.example.hotel.service.common.EmailService;
import com.example.hotel.service.user.UserService;
import com.example.hotel.util.VerificationCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import javax.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user/withoutToken")
@Api(tags = "User without token API")
@AllArgsConstructor
public class UserWithoutTokenController {

  private final UserService userService;
  private final EmailService emailService;

  /**
   * user login
   *
   * @return
   */
  @PostMapping("/login")
  @RequestMapping(value = "login", method = RequestMethod.POST)
  public ResponseResult<RegisterResponse> login(@ApiParam(value = "User details", required = true) @RequestBody UserLoginRequestDTO requestDTO) {
    try {
      return ResponseResult.ofSuccess(userService.userLogin(requestDTO));
    } catch (BizException e) {
      log.error("user login error", e);
      return ResponseResult.ofError(e.getCode().getCode(), e.getMessage());
    }


  }

  /**
   * user register
   *
   * @return
   */
  @PostMapping("/register")
  @RequestMapping(value = "register", method = RequestMethod.POST)
  public ResponseResult<RegisterResponse> register(@ApiParam(value = "Register info", required = true) @RequestBody RegisterRequestDTO requestDTO) {
    try {
      return ResponseResult.ofSuccess(userService.userSignUp(requestDTO));
    } catch (BizException e) {
      log.error("register error", e);
      return ResponseResult.ofError(e.getCode().getCode(), e.getMessage());
    }

  }

  /**
   * user forget password
   *
   * @return
   */
  @PutMapping("/forgetPassword")
  @RequestMapping(value = "forgetPassword", method = RequestMethod.PUT)
  public ResponseResult<UpdateInfoResponse> forgetPassword(@ApiParam(value = "forget password request", required = true) @RequestBody ForgetPasswordRequestDTO requestDTO) {
    try {
      return ResponseResult.ofSuccess(userService.forgetPassword(requestDTO));
    } catch (BizException e) {
      log.error("forger password error", e);
      return ResponseResult.ofError(e.getCode().getCode(), e.getMessage());
    }
  }

  /**
   * Get Verification Code
   *
   * @return
   */
  @PostMapping("/getVerificationCode ")
  @RequestMapping(value = "getVerificationCode", method = RequestMethod.POST)
  public ResponseResult getVerificationCode(@ApiParam(value = "send verification", required = true) @RequestBody EmailValidateCodeRequestDTO requestDTO,
      HttpSession session) {
    try {
      emailService.sendEmailValidateCode(requestDTO,session);
      return ResponseResult.ofSuccess();
    } catch (BizException e) {
      log.error("send verification error", e);
      return ResponseResult.ofError(e.getCode().getCode(), e.getMessage());
    }
  }
}
