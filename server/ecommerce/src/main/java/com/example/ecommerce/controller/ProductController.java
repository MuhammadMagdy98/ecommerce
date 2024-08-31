package com.example.ecommerce.controller;

import com.example.ecommerce.DTO.ProductDTO;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.service.ProduceService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class ProductController {

    private final ProduceService produceService;

    public ProductController(ProduceService productService) {
        this.produceService = productService;
    }


    @PostMapping("/add-product")
    public void addProduct(@RequestBody ProductDTO product) {


        produceService.addProduct(product).subscribe();



    }
}
