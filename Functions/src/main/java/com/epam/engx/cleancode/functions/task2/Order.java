package com.epam.engx.cleancode.functions.task2;

import com.epam.engx.cleancode.functions.task2.thirdpartyjar.Product;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

public class Order {

    private List<Product> products;

    public Double getPriceOfAvailableProducts() {
        return Optional.ofNullable(getProducts()).orElse(emptyList()).stream().
                filter(Product::isAvailable).map(Product::getProductPrice)
                .reduce(0.0, Double::sum);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
