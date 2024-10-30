package com.example.hotel.util;

import com.example.hotel.dto.Verification;
import com.example.hotel.dto.request.EmailValidateCodeRequestDTO;
import com.example.hotel.service.common.EmailService;
import java.time.LocalDateTime;
import javax.servlet.http.HttpSession;
import java.security.SecureRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class VerificationCodeUtil {

  @Autowired
  private EmailService emailService;

  private static final SecureRandom random = new SecureRandom();

  private static final int EXPIRY_MINUTES = 5; // time-out period is 5 minutes

  public void sendCodeToUser(String email) throws Exception {
    String verificationCode = generateCode();

    // send email
    EmailValidateCodeRequestDTO requestDTO = new EmailValidateCodeRequestDTO();
    requestDTO.setCode(verificationCode);
    requestDTO.setEmail(email);
    emailService.sendEmailValidateCode(requestDTO);

    // 将验证码和生成时间存储到 Session 中
    // 获取当前 Session 并将验证码保存其中
    HttpSession session = getCurrentSession();
    if (session != null) {
      session.setAttribute("cs5721_" + email, Verification.builder().code(verificationCode).createTime(
          LocalDateTime.now()).build());
    }

  }

  public static String generateCode() {
    int code = 100000 + random.nextInt(900000); // 生成 6 位随机数
    return String.valueOf(code);
  }


  // get current HttpSession
  private HttpSession getCurrentSession() {
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    return attributes != null ? attributes.getRequest().getSession(true) : null;
  }
}
