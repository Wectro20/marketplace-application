package com.andrii.dto;

import com.andrii.models.Product;
import com.andrii.models.Purchase;
import com.andrii.models.User;

public class PurchaseDto {
    private Purchase purchase;

    public PurchaseDto(Purchase purchase) {
        this.purchase = purchase;
    }

    public Integer getId() {
        return purchase.getId();
    }

    public User getUserId() {
        return purchase.getUser();
    }

    public Product getProductId() {
        return purchase.getProduct();
    }
}

