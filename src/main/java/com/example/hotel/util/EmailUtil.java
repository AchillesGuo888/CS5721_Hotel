package com.example.hotel.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailUtil {
  private static final String SMTP_HOST = "smtp.gmail.com";
  private static final String SMTP_PORT = "587";
  private static final String EMAIL_USERNAME = "your-email@gmail.com";
  private static final String EMAIL_PASSWORD = "your-password";

  public static void sendVerificationCode(String recipientEmail, String code) {
    Properties properties = new Properties();
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.smtp.host", SMTP_HOST);
    properties.put("mail.smtp.port", SMTP_PORT);

    Session session = Session.getInstance(properties, new Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(EMAIL_USERNAME, EMAIL_PASSWORD);
      }
    });

    try {
      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress(EMAIL_USERNAME));
      message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
      message.setSubject("Your Verification Code");
      message.setText("Your verification code is: " + code);

      Transport.send(message);
      System.out.println("Verification code sent successfully.");
    } catch (MessagingException e) {
      e.printStackTrace();
    }
  }
}
