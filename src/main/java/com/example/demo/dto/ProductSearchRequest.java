package com.example.demo.dto;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// ProductSearchRequest.java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSearchRequest {
    private String name;
    private Long producerId;
    private JsonNode attributes;  // now properly parsed, no encoding issues
}