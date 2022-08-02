package com.andrii.controllers;

import com.andrii.dto.PurchaseDto;
import com.andrii.exceptions.MarketplaceNotFoundException;
import com.andrii.models.Product;
import com.andrii.models.Purchase;
import com.andrii.models.User;
import com.andrii.service.ProductService;
import com.andrii.service.PurchaseService;
import com.andrii.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/purchase")
public class PurchaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PurchaseController.class);

    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<PurchaseDto> getPurchase(@PathVariable(name = "id") Integer id) {
        if (purchaseService.getPurchaseId(id) == null) {
            LOGGER.info("Can't update purchase with non-existing id" + id);
            throw new MarketplaceNotFoundException("Purchase with id: " + id + " not found");
        }
        LOGGER.info("Successfully gave an object:" + id);
        Purchase purchase = purchaseService.getPurchaseId(id);
        return new ResponseEntity<PurchaseDto>(new PurchaseDto(purchase), HttpStatus.OK);
    }

    @GetMapping(path = "/purchase/user/{id}")
    public ResponseEntity<List<PurchaseDto>> getUserPurchases(@PathVariable(name = "id") Integer id) {
        List<Purchase> purchases = purchaseService.getPurchase();
        List<PurchaseDto> purchasesDto = new ArrayList<>();
        for (Purchase purchase:purchases) {
            if (purchase.getUser().getId().equals(id)) {
                PurchaseDto purchaseDto = new PurchaseDto(purchase);
                purchasesDto.add(purchaseDto);
            }
        }
        LOGGER.info("Successfully gave an objects");
        return new ResponseEntity<List<PurchaseDto>>(purchasesDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PurchaseDto>> getPurchase() {
        List<Purchase> purchases = purchaseService.getPurchase();
        List<PurchaseDto> purchasesDto = new ArrayList<>();
        for (Purchase purchase:purchases) {
            PurchaseDto purchaseDto = new PurchaseDto(purchase);
            purchasesDto.add(purchaseDto);
        }
        LOGGER.info("Successfully gave an objects");
        return new ResponseEntity<List<PurchaseDto>>(purchasesDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PurchaseDto> createPurchase(@Valid @RequestBody Purchase purchase) {
        User user = userService.getUserId(purchase.getUser().getId());
        Product product = productService.getProductId(purchase.getProduct().getId());
        if (user.getAmountOfMoney() < product.getPrice()) {
            LOGGER.info("Can't make purchase, the user does not have the required amount of money");
            throw new MarketplaceNotFoundException("Can't make purchase, the user does not have the required amount of money");
        }
        purchaseService.addPurchase(purchase);
        user.setAmountOfMoney(user.getAmountOfMoney() - product.getPrice());
        userService.updateUser(user);
        LOGGER.info("Success added purchase");
        return new ResponseEntity<PurchaseDto>(new PurchaseDto(purchase), HttpStatus.OK);
    }

    @PutMapping(path="/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PurchaseDto> updatePurchase(@PathVariable("id")final int id, @Valid @RequestBody final Purchase purchase) {
        if (purchaseService.getPurchaseId(id) == null) {
            LOGGER.info("Can't update purchase without id - null value was passed instead of it");
            throw new MarketplaceNotFoundException("Purchase with id: " + id + " not found");
        }
        LOGGER.info("Updated an purchase with id: " + id);
        purchase.setId(id);
        purchaseService.updatePurchase(purchase);
        return new ResponseEntity<PurchaseDto>(new PurchaseDto(purchase), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Purchase> deletePurchase(@PathVariable("id") Integer id) {
        if (purchaseService.getPurchaseId(id) == null) {
            LOGGER.info("Can't delete purchase ");
            throw new MarketplaceNotFoundException("Purchase with id: " + id + " not found");
        }
        LOGGER.info("Successfully deleted purchase with id: " +id);
        purchaseService.deletePurchaseById(id);
        return ResponseEntity.noContent().build();
    }
}
