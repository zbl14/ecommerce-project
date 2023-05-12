package com.zliang14.ecommerce.dto;

import lombok.Data;

@Data
public class PaymentInfo {
  private int amount;
  private String currency;
}
