package com.example.hotel.service.impl.common;

import com.example.hotel.dto.request.EmailValidateCodeRequestDTO;
import com.example.hotel.exception.BizException;
import com.example.hotel.service.common.EmailService;
import com.example.hotel.util.FreeMarkerTemplateUtil;
import com.example.hotel.util.VerificationCodeUtil;
import javax.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {

  private final VerificationCodeUtil verificationCodeUtil;
  private final FreeMarkerTemplateUtil freeMarkerTemplateUtil;

  /**
   * send verification code email
   *
   * @param requestDTO
   * @param session
   */
  @Override
  public void sendEmailValidateCode(EmailValidateCodeRequestDTO requestDTO,
      HttpSession session) throws BizException {
    verificationCodeUtil.sendCodeToUser(requestDTO, session);
  }

}
