package com.zliang14.ecommerce.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.zliang14.ecommerce.dao.CustomerRepository;
import com.zliang14.ecommerce.dto.PaymentInfo;
import com.zliang14.ecommerce.dto.Purchase;
import com.zliang14.ecommerce.dto.PurchaseResponse;
import com.zliang14.ecommerce.entity.Customer;
import com.zliang14.ecommerce.entity.Order;
import com.zliang14.ecommerce.entity.OrderItem;

import jakarta.transaction.Transactional;

@Service
public class CheckoutServiceImpl implements CheckoutService {

  private CustomerRepository customerRepository;

  @Autowired
  public CheckoutServiceImpl(CustomerRepository customerRepository, @Value("${stripe.key.secret}") String secretKey) {
    this.customerRepository = customerRepository;

    // initialize Stripe API with secret key
    Stripe.apiKey = secretKey;

  }

  @Override
  @Transactional
  public PurchaseResponse placeOrder(Purchase purchase) {

    // retrieve the order info from dto
    Order order = purchase.getOrder();

    // generate tracking number
    String orderTrackingNumber = generateOrderTrackingNumber();
    order.setOrderTrackingNumber(orderTrackingNumber);

    // populate order with orderItems
    Set<OrderItem> orderItems = purchase.getOrderItems();
    orderItems.forEach(item -> order.add(item));

    // populate order with billingAddress and shippingAddress
    order.setBillingAddress(purchase.getBillingAddress());
    order.setShippingAddress(purchase.getShippingAddress());

    // populate customer with order
    Customer customer = purchase.getCustomer();

    // check if this is an exsiting customer
    String theEmail = customer.getEmail();
    Customer customerFromDB = customerRepository.findByEmail(theEmail);
    if (customerFromDB != null) {
      customer = customerFromDB;
    }

    customer.add(order);

    // save to the database
    customerRepository.save(customer);

    // return a response
    return new PurchaseResponse(orderTrackingNumber);
  }

  private String generateOrderTrackingNumber() {

    // generate a random UUID number (UUID version-4)
    return UUID.randomUUID().toString();
  }

  @Override
  public PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws StripeException {
    List<String> paymentMethodType = new ArrayList<>();
    paymentMethodType.add("card");

    Map<String, Object> params = new HashMap<>();
    params.put("amount", paymentInfo.getAmount());
    params.put("currency", paymentInfo.getCurrency());
    params.put("payment_method_types", paymentMethodType);
    params.put("description", "Happy Shop purchase");

    return PaymentIntent.create(params);
  }
}