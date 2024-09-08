package com.example.ecommerce.model;


import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;




import java.time.Instant;

@Data
@Table("products")
public class Product {
    @Id
    private int productId;

    private String name;

    private String description;

    private int quantity;

    private double price;

    private int categoryId;

    private int brandId;

    private Instant createdAt;

    private Instant updatedAt;
}
