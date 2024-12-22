package org.rahmi.springbootonlineshopping.repository;

import org.rahmi.springbootonlineshopping.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {}

