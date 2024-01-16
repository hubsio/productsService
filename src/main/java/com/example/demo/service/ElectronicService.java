package com.example.demo.service;

import com.example.demo.exception.DuplicateEntityException;
import com.example.demo.exception.InvalidDataException;
import com.example.demo.model.entity.Electronic;
import com.example.demo.repository.ElectronicRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ElectronicService {
    private final ElectronicRepository electronicRepository;

    public List<Electronic> getAllElectronics() {
        return electronicRepository.findAll();
    }

    public Electronic getElectronicById(Long electronicId) {
        log.info("Electronic with ID {}:", electronicId);
        Optional<Electronic> electronic = electronicRepository.findById(electronicId);
        if (electronic.isEmpty()) {
            throw new EntityNotFoundException("Electronic not found");
        }
        return electronic.get();
    }

    @Transactional
    public Electronic createElectronic(Electronic electronic) {
        if (electronic.getId() != null && electronicRepository.existsById(electronic.getId())) {
            throw new DuplicateEntityException("Electronic with this ID already exists");
        }
        log.info("Creating a new electronic: {}", electronic);
        return electronicRepository.save(electronic);
    }

    @Transactional
    public Electronic updateElectronic(Long electronicId, Electronic updatedElectronic) {
        Optional<Electronic> existingElectronic = electronicRepository.findById(electronicId);
        if (existingElectronic.isEmpty()) {
            throw new EntityNotFoundException("Electronic with given ID not found");
        }

        Electronic electronic = existingElectronic.get();
        if (updatedElectronic.getName() == null || updatedElectronic.getPrice() == null) {
            throw new InvalidDataException("Electronic data cannot be null");
        }

        electronic.setName(updatedElectronic.getName());
        electronic.setPrice(updatedElectronic.getPrice());

        log.info("Updating electronic with ID {}: {}", electronicId, existingElectronic);
        return electronicRepository.save(electronic);
    }

    @Transactional
    public void deleteElectronic(Long electronicId) {
        Optional<Electronic> electronic = electronicRepository.findById(electronicId);
        if (electronic.isEmpty()) {
            throw new EntityNotFoundException("Electronic not found");
        }
        log.info("Deleting electronic with ID {}", electronicId);
        electronicRepository.delete(electronic.get());
    }
}