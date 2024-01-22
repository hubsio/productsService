package com.example.demo.controller;

import com.example.demo.dtoConfig.EditPhoneCommandDTO;
import com.example.demo.model.entity.Phone;
import com.example.demo.model.entity.dto.PhoneDTO;
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
    public List<PhoneDTO> getAllPhones() {
        return phoneService.getAllPhones();
    }

    @GetMapping("/{id}")
    public PhoneDTO getPhoneById(@PathVariable Long id) {
        return phoneService.getPhoneById(id);
    }

    @PostMapping
    public PhoneDTO createPhone(@RequestBody PhoneDTO phoneDTO) {
        return phoneService.createPhone(phoneDTO);
    }

    @PutMapping("/{id}")
    public PhoneDTO updatePhone(@PathVariable Long id, @RequestBody EditPhoneCommandDTO editedPhoneDTO) {
        return phoneService.updatePhone(id, editedPhoneDTO);
    }

    @DeleteMapping("/{id}")
    public void deletePhone(@PathVariable Long id) {
        phoneService.deletePhone(id);
    }
}
