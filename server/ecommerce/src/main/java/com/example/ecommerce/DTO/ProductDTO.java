package com.example.ecommerce.DTO;

import lombok.Data;
import lombok.ToString;

import java.time.Instant;

@Data
@ToString
public class ProductDTO {

    private String name;

    private String description;

    private int quantity;

    private double price;

}
