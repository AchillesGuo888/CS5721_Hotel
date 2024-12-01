package com.example.hotel.util;

import java.util.regex.Pattern;

/**
 * @ClassName: ValidateUtils
 * @author: Rui Guo
 * @date: 2024/10/28
 */
public class ValidateUtils {

  /**
   * 校验登陆密码
   *
   * @param password
   * @return
   */
  private static Pattern passwordReg = Pattern
      .compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,18}$");

  public static boolean checkPassword(String password) {
    return passwordReg.matcher(password).matches();
  }

  /**
   * 校验phone
   *
   * @param password
   * @return
   */
  private static Pattern phoneReg = Pattern.compile("^1\\d{10}$");

  public static boolean checkPhone(String phone) {
    return phoneReg.matcher(phone).matches();
  }
}
