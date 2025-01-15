package com.pranav.security.service;

import com.pranav.security.model.Product;
import com.pranav.security.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public Product add(Product product) {
        log.info("Add Service");
        return repository.save(product);
    }

    public List<Product> productList() {
        log.info("ProductList Service");
        return repository.findAll();
    }

    public Product getById(int id) {
        log.info("Search Service");
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
    }

    public void delete(int id) {
        log.info("Delete Service");
        getById(id);
        repository.deleteById(id);
    }

    public Product update(int id, Product product) {
        log.info("Update Service");
        getById(id);
        product.setId(id);
        return repository.save(product);
    }

    public String greet() {
        return "Welcome";    }
}
