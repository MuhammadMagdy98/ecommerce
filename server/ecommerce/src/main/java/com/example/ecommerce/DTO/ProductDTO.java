package com.example.ecommerce.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class ProductDTO {

    @NotNull
    @NotBlank
    @Valid
    private String name;
    @NotNull
    @NotBlank
    @Valid
    private String description;
    @NotNull
    @Valid
    @Min(value = 1, message = "Quantity must be positive")
    private int quantity;
    @NotNull
    @Valid
    @Min(value = 0, message = "Price cannot be negative")
    private double price;

}
