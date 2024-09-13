package com.example.ecommerce.service;

import com.example.ecommerce.DTO.ProductDTO;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.plugin.S3Plugin;
import com.example.ecommerce.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Service
@Slf4j
public class ProduceServiceImpl implements ProduceService {



    private  final ProductRepository productRepository;
    private final S3Plugin s3Plugin;

    public ProduceServiceImpl(ProductRepository productRepository, S3Plugin s3Plugin) {
        this.productRepository = productRepository;
        this.s3Plugin = s3Plugin;
    }

    @Override
    public Mono<Void> addProduct(ProductDTO productDTO, MultipartFile file) {
        Product product = new Product();



        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());

        product.setQuantity(productDTO.getQuantity());
        product.setDescription(productDTO.getDescription());

        System.out.println(productDTO.toString());
        product.setCategoryId(1);
//        product.setBrandId(null);

        s3Plugin.uploadObject(file);


//        return productRepository.save(product).then();
        return Mono.empty();
    }
}
