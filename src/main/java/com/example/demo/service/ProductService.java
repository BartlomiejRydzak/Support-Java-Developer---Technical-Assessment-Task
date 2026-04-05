package com.example.demo.service;

import com.example.demo.dto.ProductRequest;
import com.example.demo.entity.Product;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    List<Product> findAll();

    Product save(ProductRequest product) throws IOException;

    void deleteById(Long id);

    public List<Product> search(String name, Long producerId, JsonNode attributes);
    }
