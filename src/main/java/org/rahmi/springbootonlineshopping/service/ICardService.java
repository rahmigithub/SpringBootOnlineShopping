package org.rahmi.springbootonlineshopping.service;

import org.rahmi.springbootonlineshopping.model.Card;

public interface ICardService {

     Card addToCart(Integer cardId, Integer productId, int quantity);
}
