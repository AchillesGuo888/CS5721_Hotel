package com.example.hotel;


import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;



/**
 * <pre>
 *     SENS run!
 * </pre>
 */

@SpringBootApplication(exclude = MybatisAutoConfiguration.class)
@EnableCaching

@MapperScan(basePackages = "com.example.hotel.mapper")
public class Application {

  private static final Logger log = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) {
    ApplicationContext context = SpringApplication.run(Application.class, args);
    String serverPort = context.getEnvironment().getProperty("server.port");
    log.info("SENS started at http://localhost:" + serverPort);
  }

}
