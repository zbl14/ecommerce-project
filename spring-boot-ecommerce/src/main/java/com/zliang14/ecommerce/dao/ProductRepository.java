package com.zliang14.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zliang14.ecommerce.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
