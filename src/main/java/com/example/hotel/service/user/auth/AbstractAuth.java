package com.example.hotel.service.user.auth;

import com.example.hotel.entity.User;
import com.example.hotel.exception.BizException;
import lombok.Getter;

@Getter
public abstract class AbstractAuth {

  /**
   * current user
   */
  private User currentUser;

  public AbstractAuth(User tUser) {
    this.currentUser = tUser;
  }

  /**
   * auth
   *
   * @throws BizException
   */
  public abstract void auth() throws BizException;
}
