package com.example.demo.service;

import com.example.demo.dto.ProductRequest;
import com.example.demo.entity.Producer;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProducerRepository;
import com.example.demo.repository.ProductRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    private ProductRepository productRepository;
    private ProducerRepository producerRepository;
    private final ObjectMapper objectMapper;


    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              ProducerRepository producerRepository,  // ← add this
                              ObjectMapper objectMapper) {
        this.productRepository = productRepository;
        this.producerRepository = producerRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product save(ProductRequest request) throws IOException {
        Product product = new Product();
        product.setId(request.getId());
        product.setName(request.getName());

        Producer producer = producerRepository.findById(request.getProducerId())
                .orElseThrow(() -> new RuntimeException("Producer not found"));
        product.setProducer(producer);

        product.setAttributes(request.getAttributes());
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public void deleteById(Long id){
        productRepository.deleteById(id);
    }

    public List<Product> search(String name, Long producerId, JsonNode attributes) {
        String attributesStr = attributes != null ? attributes.toString() : null;
        if (name == null && producerId == null && attributesStr == null)
            return productRepository.findAll();
        return productRepository.search(name, producerId, attributesStr);
    }
}
