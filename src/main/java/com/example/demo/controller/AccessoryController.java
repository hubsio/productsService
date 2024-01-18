package com.example.demo.controller;

import com.example.demo.model.entity.Accessory;
import com.example.demo.service.AccessoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accessories")
@RequiredArgsConstructor
public class AccessoryController {
    private final AccessoryService accessoryService;

    @PostMapping("/add/{accessoryId}/{productId}")
    public void addAccessoryToProduct(@PathVariable Long accessoryId, @PathVariable Long productId) {
        accessoryService.addAccessoryToProduct(accessoryId, productId);
    }

    @PostMapping("/add")
    public Accessory addAccessory(@RequestBody Accessory accessory) {
        return accessoryService.createAccessory(accessory);
    }

    @GetMapping("/{accessoryId}")
    public Long getAccessoryById(@PathVariable Long accessoryId) {
        return accessoryService.getAccessoryById(accessoryId).getId();
    }
}
