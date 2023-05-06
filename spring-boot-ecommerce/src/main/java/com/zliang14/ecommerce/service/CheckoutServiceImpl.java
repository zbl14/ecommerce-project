package com.zliang14.ecommerce.service;

import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zliang14.ecommerce.dao.CustomerRepository;
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
  public CheckoutServiceImpl(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
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
}