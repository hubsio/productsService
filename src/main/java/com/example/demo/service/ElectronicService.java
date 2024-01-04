package com.example.demo.service;

import com.example.demo.model.entity.Electronic;
import com.example.demo.repository.ElectronicRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ElectronicService {
    private final ElectronicRepository electronicRepository;

    public List<Electronic> getAllElectronics() {
        return electronicRepository.findAll();
    }

    public Electronic getElectronicById(Long electronicId) {
        Optional<Electronic> electronic = electronicRepository.findById(electronicId);
        if (electronic.isEmpty()) {
            throw new RuntimeException("Electronic not found");
        }
        return electronic.get();
    }

    @Transactional
    public Electronic createElectronic(Electronic electronic) {
        if (electronic.getId() != null && electronicRepository.existsById(electronic.getId())) {
            throw new RuntimeException("Electronic with this ID already exists");
        }

        return electronicRepository.save(electronic);
    }

    @Transactional
    public Electronic updateElectronic(Long electronicId, Electronic updatedElectronic) {
        Optional<Electronic> existingElectronic = electronicRepository.findById(electronicId);
        if (existingElectronic.isEmpty()) {
            throw new RuntimeException("Electronic with given ID not found");
        }

        Electronic electronic = existingElectronic.get();
        if (updatedElectronic.getName() == null || updatedElectronic.getPrice() == null) {
            throw new IllegalArgumentException("Electronic data cannot be null");
        }

        electronic.setName(updatedElectronic.getName());
        electronic.setPrice(updatedElectronic.getPrice());
        electronic.setAdditionalAccessories(updatedElectronic.getAdditionalAccessories());

        return electronicRepository.save(electronic);
    }

    @Transactional
    public void deleteElectronic(Long electronicId) {
        Optional<Electronic> electronic = electronicRepository.findById(electronicId);
        if (electronic.isEmpty()) {
            throw new RuntimeException("Electronic not found");
        }

        electronicRepository.delete(electronic.get());
    }
}
