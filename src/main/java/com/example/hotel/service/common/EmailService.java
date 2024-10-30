package com.example.hotel.service.common;


import com.example.hotel.dto.request.EmailValidateCodeRequestDTO;
import com.example.hotel.exception.BizException;

public interface EmailService {
    /**
     * 发送邮箱验证码
     * @param requestDTO
     * @throws Exception
     */
    void sendEmailValidateCode(EmailValidateCodeRequestDTO requestDTO) throws BizException;

    /**
     * 验证邮箱验证码
     * @param requestDTO
     * @throws Exception
     */
//    void verifyEmailValidateCode(EmailValidateCodeVerifyRequestDTO requestDTO) throws Exception;

    }
