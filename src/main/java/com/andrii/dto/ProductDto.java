package com.andrii.dto;

import com.andrii.models.Product;

public class ProductDto {
    private Product product;

    public ProductDto(Product product) {
        this.product = product;
    }

    public Integer getId() {
        return product.getId();
    }

    public String getProductName() {
        return product.getName();
    }

    public Float getProductPrice() {
        return product.getPrice();
    }
}
