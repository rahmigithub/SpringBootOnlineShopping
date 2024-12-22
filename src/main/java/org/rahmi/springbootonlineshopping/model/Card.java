package org.rahmi.springbootonlineshopping.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Entity
@NoArgsConstructor
@Data
@Table(name = "cards")
public class Card extends BaseEntity {

    @OneToOne
    private UserEntity user;

    @ElementCollection
    @CollectionTable(name = "card_products", joinColumns = @JoinColumn(name = "card_id"))
    @MapKeyJoinColumn(name = "product_id")
    @Column(name = "quantity")
    private Map<Product, Integer> products = new HashMap<>();

    private double totalPrice;

    public void addProduct(Product product, int quantity) {
        products.merge(product, quantity, Integer::sum);
        calculateTotalPrice();
    }

    public void removeProduct(Product product) {
        products.remove(product);
        calculateTotalPrice();
    }

    public void updateProductQuantity(Product product, int quantity) {
        if (products.containsKey(product)) {
            products.put(product, quantity);
        }
        calculateTotalPrice();
    }

    private void calculateTotalPrice() {
        totalPrice = products.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }
}
