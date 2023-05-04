package com.zliang14.ecommerce.dto;

import java.util.Set;

import com.zliang14.ecommerce.entity.Address;
import com.zliang14.ecommerce.entity.Customer;
import com.zliang14.ecommerce.entity.Order;
import com.zliang14.ecommerce.entity.OrderItem;

import lombok.Data;

@Data
public class Purchase {

  private Customer customer;
  private Address shippingAddress;
  private Address billingAddress;
  private Order order;
  private Set<OrderItem> orderItems;
}
