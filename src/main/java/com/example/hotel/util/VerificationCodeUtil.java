package com.example.hotel.util;

import com.example.hotel.dto.Verification;
import com.example.hotel.dto.request.EmailValidateCodeRequestDTO;
import java.security.SecureRandom;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Component
public class VerificationCodeUtil {

  private MailUtils mailUtils;

  private FreeMarkerTemplateUtil freeMarkerTemplateUtil;

  private static final SecureRandom random = new SecureRandom();

  private static final int EXPIRY_MINUTES = 5; // time-out period is 5 minutes

  private final static ExecutorService executorService = new ThreadPoolExecutor(0,
      Integer.MAX_VALUE,
      60L, TimeUnit.SECONDS,
      new SynchronousQueue<Runnable>());

  public String generateCode() {
    int code = 100000 + random.nextInt(900000); // 生成 6 位随机数
    return String.valueOf(code);
  }


  // get current HttpSession
  private HttpSession getCurrentSession() {
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
        .getRequestAttributes();
    return attributes != null ? attributes.getRequest().getSession(true) : null;
  }

  /**
   * get verification template
   *
   * @param code
   * @return
   */
  private String getValidateCodeHtml(String code) {
    Map<String, String> param = new HashMap<>();
    param.put("code", code);

    return freeMarkerTemplateUtil.getEmailHtml(param, "validateCodeTemplate.ftl");
  }

  public void sendCodeToUser(EmailValidateCodeRequestDTO requestDTO, HttpSession session) {
    String verificationCode = generateCode();
    requestDTO.setCode(verificationCode);
    //asynchronous execution of sending email
    executorService.execute(() -> {
      mailUtils.send(requestDTO.getEmail(), "Verification Code"
          , getValidateCodeHtml(requestDTO.getCode()), true);
      log.info("send email to {}", requestDTO.getEmail());

    });
    //store verification code in session
    if (session != null) {
      session.setAttribute("cs5721_" + requestDTO.getEmail(),
          Verification.builder().code(verificationCode).createTime(
              LocalDateTime.now()).build());
    }
  }

  public Boolean verifyCode(String code, String email) {
    HttpSession session = getCurrentSession();
    LocalDateTime generatedTime = (LocalDateTime) session.getAttribute("cs5721_" + email);
    // check verification exists
    if (generatedTime == null) {
      return false;
    }

    // check verification expiry
    Duration duration = Duration.between(generatedTime, LocalDateTime.now());
    if (duration.toMinutes() > EXPIRY_MINUTES) {
      return false;
    }
    return true;
  }
}
