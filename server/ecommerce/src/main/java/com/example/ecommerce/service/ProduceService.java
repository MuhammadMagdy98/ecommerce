package com.example.ecommerce.service;

import com.example.ecommerce.DTO.ProductDTO;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

public interface ProduceService {

     Mono<Void> addProduct(ProductDTO product, MultipartFile file);
}
