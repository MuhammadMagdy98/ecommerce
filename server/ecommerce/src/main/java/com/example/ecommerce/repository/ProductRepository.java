package com.example.ecommerce.repository;

import com.example.ecommerce.model.Product;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface ProductRepository extends R2dbcRepository<Product, Integer> {
}
