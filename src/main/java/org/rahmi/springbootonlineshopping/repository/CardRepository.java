package org.rahmi.springbootonlineshopping.repository;

import org.rahmi.springbootonlineshopping.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Integer> {}

