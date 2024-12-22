package org.rahmi.springbootonlineshopping.service;

import org.rahmi.springbootonlineshopping.model.Order;

import java.util.Map;

public interface IOrderService {

     Order createOrder(Integer customerId, Map<Integer, Integer> productQuantities);

    }
