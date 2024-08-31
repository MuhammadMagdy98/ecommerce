package com.example.ecommerce.service;

import com.example.ecommerce.DTO.ProductDTO;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Service
public class ProduceServiceImpl implements ProduceService {


    private  final ProductRepository productRepository;

    public ProduceServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Mono<Void> addProduct(ProductDTO productDTO) {
        Product product = new Product();

        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());

        product.setCreatedAt(Instant.now());
        product.setUpdatedAt(Instant.now());

        return productRepository.save(product).then();
    }
}
