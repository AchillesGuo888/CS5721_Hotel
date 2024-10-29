package com.example.hotel.util;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.security.SecureRandom;

public class VerificationCodeUtil {

  private static final SecureRandom random = new SecureRandom();

  public static void sendCodeToUser(String email, HttpSession session) {
    String verificationCode = generateCode();

    // TODO 发送验证码邮件
    //EmailService.sendVerificationCode(email, verificationCode);

    // 将验证码和生成时间存储到 Session 中
    session.setAttribute("verificationCode", verificationCode);
    session.setAttribute("codeGeneratedTime", LocalDateTime.now());
  }

  public static String generateCode() {
    int code = 100000 + random.nextInt(900000); // 生成 6 位随机数
    return String.valueOf(code);
  }

  public static boolean isCodeExpired(LocalDateTime generatedTime) {
    // 设定验证码有效期为 10 分钟
    return generatedTime.plusMinutes(10).isBefore(LocalDateTime.now());
  }
}
