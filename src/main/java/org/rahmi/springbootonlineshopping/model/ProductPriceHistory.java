package org.rahmi.springbootonlineshopping.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Entity
@Table(name = "product_price_history")
public class ProductPriceHistory extends BaseEntity {
    @ManyToOne
    private Product product;

    private double price;
    private int quantity;
    private LocalDateTime recordedAt;


}
