package com.example.demo.controller;

import com.example.demo.dtoConfig.EditElectronicCommandDTO;
import com.example.demo.model.entity.dto.AccessoryDTO;
import com.example.demo.service.AccessoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accessories")
@RequiredArgsConstructor
public class AccessoryController {
    private final AccessoryService accessoryService;

    @GetMapping
    public List<AccessoryDTO> getAllAccessories() {
        return accessoryService.getAllAccessories();
    }

    @GetMapping("/{id}")
    public AccessoryDTO getAccessoryById(@PathVariable Long id) {
        return accessoryService.getAccessoryById(id);
    }

    @PostMapping
    public AccessoryDTO createAccessory(@RequestBody AccessoryDTO accessoryDTO) {
        return accessoryService.createAccessory(accessoryDTO);
    }

    @PutMapping("/{id}")
    public AccessoryDTO updateAccessory(@PathVariable Long id, @RequestBody EditElectronicCommandDTO editedAccessoryDTO) {
        return accessoryService.updateAccessory(id, editedAccessoryDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteAccessory(@PathVariable Long id) {
        accessoryService.deleteAccessory(id);
    }
}
