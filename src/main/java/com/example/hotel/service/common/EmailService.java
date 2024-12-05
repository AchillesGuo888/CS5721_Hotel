package com.example.hotel.service.common;


import com.example.hotel.dto.request.EmailValidateCodeRequestDTO;
import com.example.hotel.exception.BizException;
import javax.servlet.http.HttpSession;

public interface EmailService {

  /**
   * send verification code
   *
   * @param requestDTO
   * @param session
   * @throws Exception
   */
  void sendEmailValidateCode(EmailValidateCodeRequestDTO requestDTO,
      HttpSession session) throws BizException;

}
