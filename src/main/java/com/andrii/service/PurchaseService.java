package com.andrii.service;

import com.andrii.models.Purchase;
import com.andrii.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.List;

@Service
@ApplicationScope
public class PurchaseService {
    @Autowired
    public PurchaseRepository purchaseRepository;

    public Purchase addPurchase(final Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    public Purchase updatePurchase(final Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    public List<Purchase> getPurchase() {
        return purchaseRepository.findAll();
    }

    public Purchase getPurchaseId(final Integer id) {
        return purchaseRepository.findById(id).orElse(null);
    }

    public void deletePurchaseById(Integer id){
        purchaseRepository.deleteById(id);
    }

}
