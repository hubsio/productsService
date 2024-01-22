package com.example.demo.service;

import com.example.demo.dtoConfig.EditPhoneCommandDTO;
import com.example.demo.exception.DuplicateEntityException;
import com.example.demo.exception.InvalidDataException;
import com.example.demo.mapper.PhoneMapper;
import com.example.demo.model.entity.Phone;
import com.example.demo.model.entity.dto.PhoneDTO;
import com.example.demo.repository.PhoneRepository;
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
public class PhoneService {
    private final PhoneRepository phoneRepository;
    private final PhoneMapper phoneMapper;

    public List<PhoneDTO> getAllPhones() {
        List<Phone> phones = phoneRepository.findAll();
        return phones.stream()
                .map(phoneMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PhoneDTO getPhoneById(Long phoneId) {
        Phone phone = phoneRepository.findById(phoneId)
                .orElseThrow(() -> new EntityNotFoundException("Phone not found"));
        return phoneMapper.toDTO(phone);
    }

    @Transactional
    public PhoneDTO createPhone(PhoneDTO phoneDTO) {
        if (phoneDTO.getId() != null && phoneRepository.existsById(phoneDTO.getId())) {
            throw new DuplicateEntityException("Phone with this ID already exists");
        }

        Phone phone = phoneMapper.toEntity(phoneDTO);
        log.info("Creating a new phone: {}", phone);
        Phone savedPhone = phoneRepository.save(phone);
        return phoneMapper.toDTO(savedPhone);
    }

    @Transactional
    public PhoneDTO updatePhone(Long phoneId, EditPhoneCommandDTO editedPhoneDTO) {
        log.info("Editing phone with ID: {}", phoneId);
        Phone existingPhone = phoneRepository.findById(phoneId)
                .orElseThrow(() -> new EntityNotFoundException("Phone with given ID not found"));

        if (editedPhoneDTO.getName() == null || editedPhoneDTO.getPrice() == null) {
            throw new InvalidDataException("Phone data cannot be null");
        }

        existingPhone.setName(editedPhoneDTO.getName());
        existingPhone.setPrice(editedPhoneDTO.getPrice());
        existingPhone.setColor(editedPhoneDTO.getColor());
        existingPhone.setBatteryCapacity(editedPhoneDTO.getBatteryCapacity());

        phoneRepository.save(existingPhone);
        log.info("Phone with ID {} edited successfully", phoneId);
        return phoneMapper.toDTO(existingPhone);
    }

    @Transactional
    public void deletePhone(Long phoneId) {
        Optional<Phone> phone = phoneRepository.findById(phoneId);
        if (phone.isEmpty()) {
            throw new EntityNotFoundException("Phone not found");
        }

        log.info("Deleting phone with ID {}", phoneId);
        phoneRepository.delete(phone.get());
    }
}
