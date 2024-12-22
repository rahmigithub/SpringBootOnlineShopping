package org.rahmi.springbootonlineshopping.repository;

import org.rahmi.springbootonlineshopping.model.ProductPriceHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductPriceHistoryRepository extends JpaRepository<ProductPriceHistory, Integer> {}

