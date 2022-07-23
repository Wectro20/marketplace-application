package com.andrii.controllers;

import com.andrii.dto.ProductDto;
import com.andrii.exceptions.MarketplaceNotFoundException;
import com.andrii.models.Product;
import com.andrii.service.ProductService;
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
@RequestMapping(path = "/product")
public class ProductController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProductDto> getProsuct(@PathVariable(name = "id") Integer id) {
        if (productService.getProductId(id) == null) {
            LOGGER.info("Can't update product with non-existing id" + id);
            throw new MarketplaceNotFoundException("Product with id: " + id + " not found");
        }
        LOGGER.info("Successfully gave an object:" + id);
        Product product = productService.getProductId(id);
        return new ResponseEntity<ProductDto>(new ProductDto(product), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProduct() {
        LOGGER.info("Successfully gave an objects");
        List<Product> products = productService.getProduct();
        List<ProductDto> productsDto = new ArrayList<>();
        for (Product product:products) {
            ProductDto productDto = new ProductDto(product);
            productsDto.add(productDto);
        }
        return new ResponseEntity<List<ProductDto>>(productsDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductDto> createUser(@Valid @RequestBody Product product) {
        productService.addProduct(product);
        LOGGER.info("Success added product");
        return new ResponseEntity<ProductDto>(new ProductDto(product), HttpStatus.OK);
    }

    @PutMapping(path="/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("id")final int id, @Valid @RequestBody final Product product) {
        if (productService.getProductId(id) == null) {
            LOGGER.info("Can't update product without id - null value was passed instead of it");
            throw new MarketplaceNotFoundException("Product with id: " + id + " not found");
        }
        LOGGER.info("Updated an product with id: " + id);
        product.setId(id);
        productService.updateProduct(product);
        return new ResponseEntity<ProductDto>(new ProductDto(product), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Integer id) {
        if (productService.getProductId(id) == null) {
            LOGGER.info("Can't delete product ");
            throw new MarketplaceNotFoundException("Product with id: " + id + " not found");
        }
        LOGGER.info("Successfully deleted product with id: " +id);
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }
}
