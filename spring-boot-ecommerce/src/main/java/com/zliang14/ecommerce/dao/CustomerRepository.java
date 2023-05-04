package com.zliang14.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zliang14.ecommerce.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
