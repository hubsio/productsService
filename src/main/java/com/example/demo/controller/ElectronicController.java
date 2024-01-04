package com.example.demo.controller;

import com.example.demo.model.entity.Electronic;
import com.example.demo.service.ElectronicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/electronics")
@RequiredArgsConstructor
public class ElectronicController {
    private final ElectronicService electronicService;

    @GetMapping
    public List<Electronic> getAllElectronics() {
        return electronicService.getAllElectronics();
    }

    @GetMapping("/{id}")
    public Electronic getElectronicById(@PathVariable Long id) {
        return electronicService.getElectronicById(id);
    }

    @PostMapping
    public Electronic createElectronic(@RequestBody Electronic electronic) {
        return electronicService.createElectronic(electronic);
    }

    @PutMapping("/{id}")
    public Electronic updateElectronic(@PathVariable Long id, @RequestBody Electronic electronic) {
        return electronicService.updateElectronic(id, electronic);
    }

    @DeleteMapping("/{id}")
    public void deleteElectronic(@PathVariable Long id) {
        electronicService.deleteElectronic(id);
    }
}
