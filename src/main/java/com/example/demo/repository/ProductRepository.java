package com.example.demo.repository;

import com.example.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>,
        JpaSpecificationExecutor<Product> {

    @Query(value = "SELECT * FROM product p WHERE " +
            "(:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:producerId IS NULL OR p.producer_id = :producerId) AND " +
            "(:attributes IS NULL OR p.attributes @> CAST(:attributes AS jsonb))",
            nativeQuery = true)
    List<Product> search(
            @Param("name") String name,
            @Param("producerId") Long producerId,
            @Param("attributes") String attributes
    );
}
