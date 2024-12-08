package com.example.hotel.service.user.auth;


import com.example.hotel.common.base.ResponseCode;
import com.example.hotel.entity.User;
import com.example.hotel.exception.BizException;
import com.example.hotel.util.Md5Util;
import lombok.Getter;

/**
 * @author Achilles email match
 */
@Getter
public class Auth4EmailPasswordMatch extends AbstractAuth {

  /**
   *
   */
  private String passwordMD5;


  public Auth4EmailPasswordMatch(User user,
      String requestPassword) {
    super(user);
    this.passwordMD5 = Md5Util.MD5(requestPassword);
  }

  /**
   * salt and md5
   *
   * @param user
   * @param requestPassword
   * @param salt
   */
  public Auth4EmailPasswordMatch(User user,
      String requestPassword, String salt) {
    super(user);
    this.passwordMD5 = Md5Util.getSaltMd5AndSha(requestPassword, salt);
  }

  @Override
  public void auth() throws BizException {
    if (!getCurrentUser().getPassword().equals(passwordMD5)) {
      throw new BizException(ResponseCode.email_password_not_match);
    }
  }
}
