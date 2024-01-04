package com.example.demo.repository;

import com.example.demo.model.entity.Computer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ComputerRepository extends JpaRepository<Computer, Long> {
}
