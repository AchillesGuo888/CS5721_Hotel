package com.example.hotel.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;



@Data
@Configuration
@ConditionalOnExpression("!'${mail}'.isEmpty()")
@ConfigurationProperties(prefix = "mail")
@ToString
public class MailPropertiesConfig {
    /**
     * SMTP
     */
    private String host;

    private String from;


    private String user;
    /**
     * password or grant code
     */
    private String pass;

    private String port;
    private Boolean sslEnable;
    private String socketFactoryClass;
    private Boolean socketFactoryFallback;
    private Integer socketFactoryPort;
}
