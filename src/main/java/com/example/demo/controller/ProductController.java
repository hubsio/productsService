package com.example.demo.controller;

import com.example.demo.model.entity.dto.ProductInfoDTO;
import com.example.demo.model.entity.dto.ProductDTO;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{productId}")
    public ProductInfoDTO getProductInfo(@PathVariable Long productId) {
        return productService.getProductInfo(productId);
    }
}