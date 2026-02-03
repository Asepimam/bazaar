package com.bazaar.api.bazaar.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    
    @PostMapping
    public String createProduct() {
        return "Product created successfully!";
    }


    @GetMapping("{id}")
    public String getProductById(@PathVariable String id) {
        return "Product details";
    }
}
