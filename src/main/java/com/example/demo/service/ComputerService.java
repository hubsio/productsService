package com.example.demo.service;

import com.example.demo.model.entity.Computer;
import com.example.demo.repository.ComputerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ComputerService {
    private final ComputerRepository computerRepository;

    public List<Computer> getAllComputers() {
        return computerRepository.findAll();
    }

    public Computer getComputerById(Long computerId) {
        Optional<Computer> computer = computerRepository.findById(computerId);
        if (computer.isEmpty()) {
            throw new RuntimeException("Computer not found");
        }
        return computer.get();
    }

    @Transactional
    public Computer createComputer(Computer computer) {
        if (computer.getId() != null && computerRepository.existsById(computer.getId())) {
            throw new RuntimeException("Computer with this ID already exists");
        }

        return computerRepository.save(computer);
    }

    @Transactional
    public Computer updateComputer(Long computerId, Computer updatedComputer) {
        Optional<Computer> existingComputer = computerRepository.findById(computerId);
        if (existingComputer.isEmpty()) {
            throw new RuntimeException("Computer with given ID not found");
        }

        Computer computer = existingComputer.get();
        if (updatedComputer.getName() == null || updatedComputer.getPrice() == null) {
            throw new IllegalArgumentException("Computer data cannot be null");
        }

        computer.setName(updatedComputer.getName());
        computer.setPrice(updatedComputer.getPrice());
        computer.setProcessor(updatedComputer.getProcessor());
        computer.setRam(updatedComputer.getRam());
        computer.setAdditionalAccessories(updatedComputer.getAdditionalAccessories());

        return computerRepository.save(computer);
    }

    @Transactional
    public void deleteComputer(Long computerId) {
        Optional<Computer> computer = computerRepository.findById(computerId);
        if (computer.isEmpty()) {
            throw new RuntimeException("Computer not found");
        }

        computerRepository.delete(computer.get());
    }
}

