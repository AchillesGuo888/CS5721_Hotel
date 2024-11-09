package com.example.hotel.controller;


import com.example.hotel.common.base.ResponseCode;
import com.example.hotel.common.base.ResponseResult;
import com.example.hotel.dto.request.ModifyUserInfoRequestDTO;
import com.example.hotel.dto.request.PasswordModifyRequestDTO;

import com.example.hotel.dto.response.UpdateInfoResponse;
import com.example.hotel.dto.response.UserInfoResponse;
import com.example.hotel.exception.BizException;
import com.example.hotel.service.user.UserService;
import com.example.hotel.util.JwtUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/user")
@Api(tags = "User API")
public class UserController {


  @Autowired
  private JwtUtil jwtUtil;

  @Autowired
  private UserService userService;

  /**
   * user logout
   *
   * @return
   */
  @PostMapping("/logout")
  @RequestMapping(value = "userLogout", method = RequestMethod.POST)
  public ResponseResult userLogout(@RequestHeader("Authorization") String token) {
      userService.userLogout(token);
      return ResponseResult.ofSuccess();
  }

  /**
   * query user info
   *
   * @return
   */
  @GetMapping("/queryUserInfo")
  @RequestMapping(value = "queryUserInfo", method = RequestMethod.GET)
  public ResponseResult<UserInfoResponse> queryUserInfo(@RequestHeader("Authorization") String token) {
    try {
      return ResponseResult.ofSuccess(userService.getUserInfo(token));
    } catch (BizException e) {
      log.error("query user info error", e);
      return ResponseResult.ofError(e.getCode().getCode(), e.getMessage());
    }
  }

  /**
   * query user info
   *
   * @return
   */
  @PostMapping("/modifyUserInfo")
  @RequestMapping(value = "modifyUserInfo", method = RequestMethod.POST)
  public ResponseResult modifyUserInfo(@RequestHeader("Authorization") String token, @ApiParam(value = "User details", required = true) @RequestBody ModifyUserInfoRequestDTO requestDTO) {
    if (token != null && token.startsWith("Bearer ")) {
      token = token.substring(7);
      String userId = jwtUtil.getUserIdFromToken(token);
    }
    try {
      return ResponseResult.ofSuccess(userService.updateUserInfo(requestDTO,token));
    } catch (BizException e) {
      log.error("modify user info error", e);
      return ResponseResult.ofError(e.getCode().getCode(), e.getMessage());
    }
  }

  @PostMapping(value = "/modifyPassword")
  @ApiOperation("modifyPassword")
  public ResponseResult<UpdateInfoResponse> modifyPassword(@RequestHeader("Authorization") String token,
      @ApiParam(value = "modify user password", required = true) @RequestBody PasswordModifyRequestDTO requestDTO) {
    try {
      return ResponseResult.ofSuccess(userService.modifyPassword(requestDTO));
    } catch (BizException e) {
      log.error("modify password error", e);
      return ResponseResult.ofError(e.getCode().getCode(), e.getMessage());
    }
  }


}
