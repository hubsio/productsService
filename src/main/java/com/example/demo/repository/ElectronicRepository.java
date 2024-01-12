package com.example.demo.repository;

import com.example.demo.model.entity.Electronic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface ElectronicRepository extends JpaRepository<Electronic, Long> {
}
