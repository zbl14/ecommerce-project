package com.zliang14.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zliang14.ecommerce.dto.Purchase;
import com.zliang14.ecommerce.dto.PurchaseResponse;
import com.zliang14.ecommerce.service.CheckoutService;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

  private CheckoutService checkoutService;

  @Autowired
  public CheckoutController(CheckoutService checkoutService) {
    this.checkoutService = checkoutService;
  }

  @PostMapping("/purchase")
  public PurchaseResponse placeOrder(@RequestBody Purchase purchase) {

    PurchaseResponse purchaseResponse = checkoutService.placeOrder(purchase);

    return purchaseResponse;
  }

}