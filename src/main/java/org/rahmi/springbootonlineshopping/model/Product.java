package org.rahmi.springbootonlineshopping.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name = "products")
public class Product extends BaseEntity {

    private String name;
    private double price;
    private int stock;

}
