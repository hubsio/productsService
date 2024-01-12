package com.example.demo.service;

import com.example.demo.model.entity.Phone;
import com.example.demo.repository.PhoneRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PhoneService {
    private final PhoneRepository phoneRepository;

    public List<Phone> getAllPhones() {
        return phoneRepository.findAll();
    }

    public Phone getPhoneById(Long phoneId) {
        return phoneRepository.findById(phoneId)
                .orElseThrow(() -> new RuntimeException("Phone not found"));
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
        Phone existingPhone = phoneRepository.findById(phoneId)
                .orElseThrow(() -> new RuntimeException("Phone with given ID not found"));

        if (updatedPhone.getName() == null || updatedPhone.getPrice() == null) {
            throw new IllegalArgumentException("Phone data cannot be null");
        }

        existingPhone.setName(updatedPhone.getName());
        existingPhone.setPrice(updatedPhone.getPrice());
        existingPhone.setColor(updatedPhone.getColor());
        existingPhone.setBatteryCapacity(updatedPhone.getBatteryCapacity());
        existingPhone.setAdditionalAccessories(updatedPhone.getAdditionalAccessories());
        existingPhone.setAdditionalAccessories(updatedPhone.getAdditionalAccessories());

        return phoneRepository.save(existingPhone);
    }

    @Transactional
    public void deletePhone(Long phoneId) {
        Phone phone = phoneRepository.findById(phoneId)
                .orElseThrow(() -> new RuntimeException("Phone not found"));
        phoneRepository.delete(phone);
    }
}
