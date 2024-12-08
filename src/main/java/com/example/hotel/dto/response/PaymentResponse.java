package com.example.hotel.dto.response;

import lombok.Data;

@Data
public class PaymentResponse {

  private String status;
  private String transactionId;
  private String message;
  private String amount;
  private String currency;

  // Getters and Setters
}

