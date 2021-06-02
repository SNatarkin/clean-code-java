package com.epam.engx.cleancode.functions.task2;

import com.epam.engx.cleancode.functions.task2.thirdpartyjar.Product;

import java.util.List;

public class Order {

    private List<Product> products;

    public Double getPriceOfAvailableProducts() {
        return getProducts() == null ? 0.0 : getProducts().stream().filter(Product::isAvailable)
                .mapToDouble(Product::getProductPrice).sum();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
