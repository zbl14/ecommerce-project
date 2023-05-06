package com.zliang14.ecommerce.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.zliang14.ecommerce.entity.State;

@RepositoryRestResource
public interface StateRepository extends JpaRepository<State, Integer> {

  // To retrieve states for a given country code
  List<State> findByCountryCode(@Param("code") String code);
}
