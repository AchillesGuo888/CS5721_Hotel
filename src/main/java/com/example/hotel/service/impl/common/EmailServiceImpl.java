package com.example.hotel.service.impl.common;

import com.example.hotel.dto.request.EmailValidateCodeRequestDTO;
import com.example.hotel.exception.BizException;
import com.example.hotel.service.common.EmailService;

import com.example.hotel.util.FreeMarkerTemplateUtil;
import com.example.hotel.util.MailUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;


@Service
@Slf4j
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final MailUtils mailUtils;
    private final FreeMarkerTemplateUtil freeMarkerTemplateUtil;

    private final static ExecutorService executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
            60L, TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>());
    /**
     * send verification code email
     * @param requestDTO
     */
    @Override
    public void sendEmailValidateCode(EmailValidateCodeRequestDTO requestDTO) throws BizException {

        //异步执行
        executorService.execute(()->{
            mailUtils.send(requestDTO.getEmail(),"Verification Code"
                    ,getValidateCodeHtml(requestDTO.getCode()),true);
            log.info("send email to {}", requestDTO.getEmail());
            log.info("redis setting end");
        });

    }

    /**
     * check verification code
     * @param requestDTO
     */
//    @Override
//    public void verifyEmailValidateCode(EmailValidateCodeVerifyRequestDTO requestDTO) throws BizException {
//      CodeScene codeScene = CodeScene.getByCode(requestDTO.getType());
//      //验证类型是否存在，如果不存在，抛出异常
//      if(codeScene==null){
//          throw new BizException(ResponseCode.param_error);
//      }
//      String validateCode = redisUtils.get(CacheKeys.getEmailCodeKey(requestDTO.getEmail(),codeScene.getCode()));
//      //检查redis中的验证码是否过期
//      if(StringUtils.isEmpty(validateCode)){
//          throw new BizException(ResponseCode.validate_code_expire);
//      //验证码是否相等
//      }else if(!validateCode.equals(requestDTO.getValidateCode())){
//          throw new BizException(ResponseCode.code_false);
//      }else{
//          return;
//      }
//    }






    /**
     * get verification template
     * @param code
     * @return
     */
    private String getValidateCodeHtml(String code){
        Map<String, String> param = new HashMap<>();
        param.put("code",code);

        return freeMarkerTemplateUtil.getEmailHtml(param,"validateCodeTemplate.ftl");
    }



}
