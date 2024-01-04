package com.example.demo.controller;

import com.example.demo.model.entity.Phone;
import com.example.demo.service.PhoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/phones")
@RequiredArgsConstructor
public class PhoneController {
    private final PhoneService phoneService;

    @GetMapping
    public List<Phone> getAllPhones() {
        return phoneService.getAllPhones();
    }

    @GetMapping("/{id}")
    public Phone getPhoneById(@PathVariable Long id) {
        return phoneService.getPhoneById(id);
    }

    @PostMapping
    public Phone createPhone(@RequestBody Phone phone) {
        return phoneService.createPhone(phone);
    }

    @PutMapping("/{id}")
    public Phone updatePhone(@PathVariable Long id, @RequestBody Phone phone) {
        return phoneService.updatePhone(id, phone);
    }

    @DeleteMapping("/{id}")
    public void deletePhone(@PathVariable Long id) {
        phoneService.deletePhone(id);
    }
}
