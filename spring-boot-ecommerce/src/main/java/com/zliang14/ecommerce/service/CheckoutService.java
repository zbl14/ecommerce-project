package com.zliang14.ecommerce.service;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.zliang14.ecommerce.dto.PaymentInfo;
import com.zliang14.ecommerce.dto.Purchase;
import com.zliang14.ecommerce.dto.PurchaseResponse;

public interface CheckoutService {

  PurchaseResponse placeOrder(Purchase purchase);

  PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws StripeException;
}
