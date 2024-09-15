package com.example.ecommerce.controller;

import com.example.ecommerce.DTO.ProductDTO;
import com.example.ecommerce.constant.Url;
import com.example.ecommerce.service.ProduceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(Url.PRODUCT)
public class ProductController {

    private final ProduceService produceService;

    public ProductController(ProduceService productService) {
        this.produceService = productService;
    }


    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void addProduct(@RequestPart("product") String productJson, @RequestPart("file") MultipartFile file) {


        // Convert product JSON string to ProductDTO object using ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        ProductDTO productDTO = null;
        try {
            productDTO = objectMapper.readValue(productJson, ProductDTO.class);
        } catch (JsonProcessingException e) {
            System.out.println("exception");
        }
        produceService.addProduct(productDTO, file).subscribe();



    }
}
