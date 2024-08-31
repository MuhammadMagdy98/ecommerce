package com.example.ecommerce.service;

import com.example.ecommerce.DTO.ProductDTO;
import reactor.core.publisher.Mono;

public interface ProduceService {

     Mono<Void> addProduct(ProductDTO product);
}
