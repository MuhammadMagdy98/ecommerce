package com.example.ecommerce.model;


import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Data
@Table("products")
public class Product {
    @Id

    int id;

    String name;

    String description;

    int quantity;

    double price;

    int categoryId;

    int brandId;

    Instant createdAt;

    Instant updatedAt;
}
