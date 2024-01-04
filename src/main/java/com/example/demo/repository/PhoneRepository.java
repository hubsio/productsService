package com.example.demo.repository;

import com.example.demo.model.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface PhoneRepository extends JpaRepository<Phone, Long> {
}
