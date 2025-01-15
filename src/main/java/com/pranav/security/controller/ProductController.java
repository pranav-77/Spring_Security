package com.pranav.security.controller;

import com.pranav.security.model.Product;
import com.pranav.security.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping("/message")
    public String greet(HttpServletRequest request) {
        return service.greet() + " " + request.getSession().getId();
    }

    @PostMapping
    public Product add(@RequestBody Product product) {
        log.info("Add Controller");
        return service.add(product);
    }

    @GetMapping("/csrf")
    public CsrfToken csrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @GetMapping
    public List<Product> productList() {
        log.info("List of Products Controller");
        return service.productList();
    }

    @GetMapping("/search/{id}")
    public Product getById(@PathVariable int id) {
        log.info("Search Controller");
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        log.info("Delete Controller");
        service.delete(id);
        return "DELETED";
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable int id, @RequestBody Product product) {
        log.info("Update Controller");
        return service.update(id, product);
    }
}
