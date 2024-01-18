package com.example.demo.service;

import com.example.demo.model.entity.Accessory;
import com.example.demo.model.entity.Product;
import com.example.demo.repository.AccessoryRepository;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccessoryService {
    private final AccessoryRepository accessoryRepository;
    private final ProductService productService;
    public void addAccessoryToProduct(Long accessoryId, Long productId) {
        Accessory accessory = accessoryRepository.findById(accessoryId)
                .orElseThrow(() -> new EntityNotFoundException("Accessory not found"));

        Product product = productService.getProductById(productId);
        accessory.setProduct(product);
        accessoryRepository.save(accessory);
    }

    public Accessory createAccessory(Accessory accessory) {
        return accessoryRepository.save(accessory);
    }

    public Accessory getAccessoryById(Long id) {
        return accessoryRepository.findById(id)
                .orElseThrow(() -> new com.example.demo.exception.EntityNotFoundException("Product not found"));
    }
}
