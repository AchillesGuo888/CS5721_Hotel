package com.example.hotel.service.common;


import com.example.hotel.dto.request.EmailValidateCodeRequestDTO;
import com.example.hotel.exception.BizException;
import javax.servlet.http.HttpSession;

public interface EmailService {
    /**
     * 发送邮箱验证码
     * @param requestDTO
     * @param session
     * @throws Exception
     */
    void sendEmailValidateCode(EmailValidateCodeRequestDTO requestDTO,
        HttpSession session) throws BizException;

    /**
     * 验证邮箱验证码
     * @param requestDTO
     * @throws Exception
     */
//    void verifyEmailValidateCode(EmailValidateCodeVerifyRequestDTO requestDTO) throws Exception;

    }
