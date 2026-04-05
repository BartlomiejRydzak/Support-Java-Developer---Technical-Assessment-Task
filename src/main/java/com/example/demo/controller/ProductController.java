package com.example.demo.controller;

import com.example.demo.dto.ProductRequest;
import com.example.demo.dto.ProductSearchRequest;
import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> findAll(){
        return productService.findAll();
    }

    @PostMapping("/products/search")
    public List<Product> search(@Valid @RequestBody ProductSearchRequest request) {
        return productService.search(request.getName(), request.getProducerId(), request.getAttributes());
    }

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public Product save(@Valid @RequestBody ProductRequest product) throws IOException {
        return productService.save(product);
    }

    @PutMapping("/products")
    public Product updateProduct(@Valid @RequestBody ProductRequest product) throws IOException {
        return productService.save(product);
    }

    @DeleteMapping("/products/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductById(@PathVariable Long id){
        productService.deleteById(id);
    }
}
