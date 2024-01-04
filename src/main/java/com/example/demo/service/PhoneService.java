package com.example.demo.service;

import com.example.demo.model.entity.Phone;
import com.example.demo.repository.PhoneRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PhoneService {
    private final PhoneRepository phoneRepository;

    public List<Phone> getAllPhones() {
        return phoneRepository.findAll();
    }

    public Phone getPhoneById(Long phoneId) {
        Optional<Phone> phone = phoneRepository.findById(phoneId);
        if (phone.isEmpty()) {
            throw new RuntimeException("Phone not found");
        }
        return phone.get();
    }

    @Transactional
    public Phone createPhone(Phone phone) {
        if (phone.getId() != null && phoneRepository.existsById(phone.getId())) {
            throw new RuntimeException("Phone with this ID already exists");
        }

        return phoneRepository.save(phone);
    }

    @Transactional
    public Phone updatePhone(Long phoneId, Phone updatedPhone) {
        Optional<Phone> existingPhone = phoneRepository.findById(phoneId);
        if (existingPhone.isEmpty()) {
            throw new RuntimeException("Phone with given ID not found");
        }

        Phone phone = existingPhone.get();
        if (updatedPhone.getName() == null || updatedPhone.getPrice() == null) {
            throw new IllegalArgumentException("Phone data cannot be null");
        }

        phone.setName(updatedPhone.getName());
        phone.setPrice(updatedPhone.getPrice());
        phone.setColor(updatedPhone.getColor());
        phone.setBatteryCapacity(updatedPhone.getBatteryCapacity());
        phone.setAdditionalAccessories(updatedPhone.getAdditionalAccessories());

        return phoneRepository.save(phone);
    }

    @Transactional
    public void deletePhone(Long phoneId) {
        Optional<Phone> phone = phoneRepository.findById(phoneId);
        if (phone.isEmpty()) {
            throw new RuntimeException("Phone not found");
        }

        phoneRepository.delete(phone.get());
    }
}
