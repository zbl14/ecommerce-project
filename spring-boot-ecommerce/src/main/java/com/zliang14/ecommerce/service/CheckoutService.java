package com.zliang14.ecommerce.service;

import com.zliang14.ecommerce.dto.Purchase;
import com.zliang14.ecommerce.dto.PurchaseResponse;

public interface CheckoutService {

  PurchaseResponse placeOrder(Purchase purchase);
}
