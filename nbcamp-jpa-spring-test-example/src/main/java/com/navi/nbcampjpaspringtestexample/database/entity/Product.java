package com.navi.nbcampjpaspringtestexample.database.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int price;

    private double discountRate;

    public double getDiscountedPrice() {
        return price * (1 - discountRate);
    }

    public Product(String name, int price, double discountRate) {
        if (discountRate < 0.0 || discountRate > 1.0) {
            throw new IllegalArgumentException("Invalid discount rate");
        }

        this.name = name;
        this.price = price;
        this.discountRate = discountRate;
    }
}
