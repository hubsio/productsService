package com.example.demo.service;

import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.model.entity.Product;
import com.example.demo.model.entity.dto.ProductInfoDTO;
import com.example.demo.model.entity.dto.ProductDTO;
import com.example.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductDTO getProductById(Long id) {
        log.info("Product with ID {}:", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        return productMapper.toDTO(product);
    }

    public ProductInfoDTO getProductInfo(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return new ProductInfoDTO(product.getId(), product.getName(), product.getPrice());
    }
}
