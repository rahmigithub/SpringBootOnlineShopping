package org.rahmi.springbootonlineshopping.service.Impl;


import org.rahmi.springbootonlineshopping.model.Card;
import org.rahmi.springbootonlineshopping.model.Product;
import org.rahmi.springbootonlineshopping.repository.CardRepository;
import org.rahmi.springbootonlineshopping.repository.ProductRepository;
import org.rahmi.springbootonlineshopping.service.ICardService;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImp implements ICardService {


    private final CardRepository cardRepository;
    private final ProductRepository productRepository;

    public CardServiceImp(CardRepository cardRepository, CardRepository cardRepository1, ProductRepository productRepository) {
        this.cardRepository = cardRepository1;
        this.productRepository = productRepository;
    }

    public Card addToCart(Integer cardId, Integer productId, int quantity) {
        Card cart = cardRepository.findById(cardId).orElseThrow();
        Product product = productRepository.findById(productId).orElseThrow();
        cart.addProduct(product, quantity);
        return cardRepository.save(cart);
    }
}
