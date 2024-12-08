package com.example.hotel.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;

import com.example.hotel.config.MailPropertiesConfig;
import java.io.File;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
@Slf4j
public class MailUtils {
    @Autowired
    private MailPropertiesConfig mailPropertiesConfig;

    /**
     * send email
     * @param to
     * @param subject
     * @param content
     * @param isHtml
     */
    public void send(String to, String subject, String content,boolean isHtml){
        send(CollUtil.newArrayList(to), subject, content, isHtml,null);
    }

    /**
     * send email
     * @param to
     * @param subject
     * @param content
     * @param isHtml
     * @param files
     */
    public void send(List<String> to, String subject, String content, boolean isHtml, File... files){
        MailAccount account = getMailAccount(mailPropertiesConfig);
//        log.info(mailPropertiesConfig.toString());

        MailUtil.send(account, to, subject, content, isHtml,files);
    }

    private MailAccount getMailAccount(MailPropertiesConfig mailPropertiesConfig){
        MailAccount mailAccount = new MailAccount();
        mailAccount.setHost(mailPropertiesConfig.getHost());
        mailAccount.setFrom(mailPropertiesConfig.getFrom());
        mailAccount.setUser(mailPropertiesConfig.getUser());
        mailAccount.setPass(mailPropertiesConfig.getPass());
        mailAccount.setPort(Integer.parseInt(mailPropertiesConfig.getPort()));
        mailAccount.setSslEnable(mailPropertiesConfig.getSslEnable());
        mailAccount.setSocketFactoryClass(mailPropertiesConfig.getSocketFactoryClass());
        mailAccount.setSocketFactoryFallback(mailPropertiesConfig.getSocketFactoryFallback());
        mailAccount.setSocketFactoryPort(mailPropertiesConfig.getSocketFactoryPort());
        return mailAccount;
    }
}
