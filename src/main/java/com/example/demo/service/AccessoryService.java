package com.example.demo.service;

import com.example.demo.dtoConfig.EditElectronicCommandDTO;
import com.example.demo.exception.DuplicateEntityException;
import com.example.demo.exception.InvalidDataException;
import com.example.demo.mapper.AccessoryMapper;
import com.example.demo.model.entity.Accessory;
import com.example.demo.model.entity.dto.AccessoryDTO;
import com.example.demo.repository.AccessoryRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccessoryService {
    private final AccessoryRepository accessoryRepository;
    private final AccessoryMapper accessoryMapper;

    public List<AccessoryDTO> getAllAccessories() {
        List<Accessory> accessories = accessoryRepository.findAll();
        return accessories.stream()
                .map(accessoryMapper::toDTO)
                .collect(Collectors.toList());
    }

    public AccessoryDTO getAccessoryById(Long electronicId) {
        Accessory accessory = accessoryRepository.findById(electronicId)
                .orElseThrow(() -> new EntityNotFoundException("Electronic not found"));
        return accessoryMapper.toDTO(accessory);
    }

    @Transactional
    public AccessoryDTO createAccessory(AccessoryDTO accessoryDTO) {
        if (accessoryDTO.getId() != null && accessoryRepository.existsById(accessoryDTO.getId())) {
            throw new DuplicateEntityException("Electronic with this ID already exists");
        }

        Accessory accessory = accessoryMapper.toEntity(accessoryDTO);
        log.info("Creating a new electronic: {}", accessory);
        Accessory savedAccessory = accessoryRepository.save(accessory);
        return accessoryMapper.toDTO(savedAccessory);
    }

    @Transactional
    public AccessoryDTO updateAccessory(Long electronicId, EditElectronicCommandDTO editedElectronicDTO) {
        log.info("Editing electronic with ID: {}", electronicId);
        Accessory existingAccessory = accessoryRepository.findById(electronicId)
                .orElseThrow(() -> new EntityNotFoundException("Electronic with given ID not found"));

        if (editedElectronicDTO.getName() == null || editedElectronicDTO.getPrice() == null) {
            throw new InvalidDataException("Electronic data cannot be null");
        }

        existingAccessory.setName(editedElectronicDTO.getName());
        existingAccessory.setPrice(editedElectronicDTO.getPrice());

        accessoryRepository.save(existingAccessory);
        log.info("Electronic with ID {} edited successfully", electronicId);
        return accessoryMapper.toDTO(existingAccessory);
    }

    @Transactional
    public void deleteAccessory(Long electronicId) {
        Optional<Accessory> electronic = accessoryRepository.findById(electronicId);
        if (electronic.isEmpty()) {
            throw new EntityNotFoundException("Electronic not found");
        }
        log.info("Deleting electronic with ID {}", electronicId);
        accessoryRepository.delete(electronic.get());
    }
}