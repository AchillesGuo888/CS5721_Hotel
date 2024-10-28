package com.example.hotel.controller;


import com.example.hotel.common.base.ResponseCode;
import com.example.hotel.common.base.ResponseResult;
import com.example.hotel.dto.request.ModifyUserInfoRequestDTO;
import com.example.hotel.util.JwtUtil;

import io.swagger.annotations.Api;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
@RestController
@RequestMapping("/user")
@Api(tags = "User API")
public class UserController {


  @Autowired
  private JwtUtil jwtUtil;

  /**
   * user logout
   *
   * @return
   */
  @PostMapping("/logout")
  @RequestMapping(value = "userLogout", method = RequestMethod.POST)
  public ResponseResult userLogout(@RequestHeader("Authorization") String token) {
    if (token != null && token.startsWith("Bearer ")) {
      token = token.substring(7);
      jwtUtil.blacklistToken(token); // put Token into blacklist
      SecurityContextHolder.clearContext();

    }
      return ResponseResult.ofSuccess();
  }

  /**
   * query user info
   *
   * @return
   */
  @GetMapping("/queryUserInfo")
  @RequestMapping(value = "queryUserInfo", method = RequestMethod.GET)
  public ResponseResult queryUserInfo(@RequestHeader("Authorization") String token) {
    if (token != null && token.startsWith("Bearer ")) {
      token = token.substring(7);
      String userId = jwtUtil.getUserIdFromToken(token);
    }
    return ResponseResult.ofSuccess();
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
    return ResponseResult.ofSuccess();
  }


}
