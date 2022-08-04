package com.andrii.service;

import com.andrii.models.Product;
import com.andrii.models.User;
import com.andrii.repository.ProductRepository;
import com.andrii.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.List;

@Service
@ApplicationScope
public class ProductService {
    @Autowired
    public ProductRepository productRepository;

    public Product addProduct(final Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(final Product product) {
        return productRepository.save(product);
    }

    public List<Product> getProduct() {
        return productRepository.findAll();
    }

    public Product getProductId(final Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    public void deleteProductById(Integer id){
        productRepository.deleteById(id);
    }
}
