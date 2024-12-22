package org.rahmi.springbootonlineshopping.service.Impl;

import org.rahmi.springbootonlineshopping.model.Order;
import org.rahmi.springbootonlineshopping.model.Product;
import org.rahmi.springbootonlineshopping.repository.OrderRepository;
import org.rahmi.springbootonlineshopping.repository.ProductRepository;
import org.rahmi.springbootonlineshopping.service.IOrderService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OrderServiceImp implements IOrderService {


    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    public OrderServiceImp(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Order createOrder(Integer customerId, Map<Integer, Integer> productQuantities) {
        Order order = new Order();
        productQuantities.forEach((productId, quantity) -> {
            Product product = productRepository.findById(productId).orElseThrow();
            order.addProduct(product, quantity);
        });
        return orderRepository.save(order);
    }



}
