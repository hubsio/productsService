package com.example.demo.service;

import com.example.demo.exception.DuplicateEntityException;
import com.example.demo.exception.InvalidDataException;
import com.example.demo.model.entity.Phone;
import com.example.demo.repository.PhoneRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PhoneService {
    private final PhoneRepository phoneRepository;

    public List<Phone> getAllPhones() {
        return phoneRepository.findAll();
    }

    public Phone getPhoneById(Long phoneId) {
        log.info("Phone with ID {}:", phoneId);
        return phoneRepository.findById(phoneId)
                .orElseThrow(() -> new EntityNotFoundException("Phone not found"));
    }

    @Transactional
    public Phone createPhone(Phone phone) {
        if (phone.getId() != null && phoneRepository.existsById(phone.getId())) {
            throw new DuplicateEntityException("Phone with this ID already exists");
        }
        log.info("Creating a new phone: {}", phone);
        return phoneRepository.save(phone);
    }

    @Transactional
    public Phone updatePhone(Long phoneId, Phone updatedPhone) {
        Phone existingPhone = phoneRepository.findById(phoneId)
                .orElseThrow(() -> new EntityNotFoundException("Phone with given ID not found"));

        if (updatedPhone.getName() == null || updatedPhone.getPrice() == null) {
            throw new InvalidDataException("Phone data cannot be null");
        }

        existingPhone.setName(updatedPhone.getName());
        existingPhone.setPrice(updatedPhone.getPrice());
        existingPhone.setColor(updatedPhone.getColor());
        existingPhone.setBatteryCapacity(updatedPhone.getBatteryCapacity());
        existingPhone.setAdditionalAccessories(updatedPhone.getAdditionalAccessories());
        existingPhone.setAdditionalAccessories(updatedPhone.getAdditionalAccessories());

        log.info("Updating phone with ID {}: {}", phoneId, existingPhone);
        return phoneRepository.save(existingPhone);
    }

    @Transactional
    public void deletePhone(Long phoneId) {
        Phone phone = phoneRepository.findById(phoneId)
                .orElseThrow(() -> new EntityNotFoundException("Phone not found"));
        log.info("Deleting phone with ID {}:", phoneId);
        phoneRepository.delete(phone);
    }
}
