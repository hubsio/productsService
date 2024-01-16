package com.example.demo.service;

import com.example.demo.exception.DuplicateEntityException;
import com.example.demo.exception.InvalidDataException;
import com.example.demo.model.entity.Computer;
import com.example.demo.repository.ComputerRepository;
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
public class ComputerService {
    private final ComputerRepository computerRepository;

    public List<Computer> getAllComputers() {
        return computerRepository.findAll();
    }

    public Computer getComputerById(Long computerId) {
        log.info("Electronic with ID {}:", computerId);
        Optional<Computer> computer = computerRepository.findById(computerId);
        if (computer.isEmpty()) {
            throw new EntityNotFoundException("Computer not found");
        }
        return computer.get();
    }

    @Transactional
    public Computer createComputer(Computer computer) {
        if (computer.getId() != null && computerRepository.existsById(computer.getId())) {
            throw new DuplicateEntityException("Computer with this ID already exists");
        }
        log.info("Creating a new computer: {}", computer);
        return computerRepository.save(computer);
    }

    @Transactional
    public Computer updateComputer(Long computerId, Computer updatedComputer) {
        Optional<Computer> existingComputer = computerRepository.findById(computerId);
        if (existingComputer.isEmpty()) {
            throw new EntityNotFoundException("Computer with given ID not found");
        }

        Computer computer = existingComputer.get();
        if (updatedComputer.getName() == null || updatedComputer.getPrice() == null) {
            throw new InvalidDataException("Computer data cannot be null");
        }

        computer.setName(updatedComputer.getName());
        computer.setPrice(updatedComputer.getPrice());
        computer.setProcessor(updatedComputer.getProcessor());
        computer.setRam(updatedComputer.getRam());

        log.info("Updating computer with ID {}: {}", computerId, existingComputer);
        return computerRepository.save(computer);
    }

    @Transactional
    public void deleteComputer(Long computerId) {
        Optional<Computer> computer = computerRepository.findById(computerId);
        if (computer.isEmpty()) {
            throw new EntityNotFoundException("Computer not found");
        }
        log.info("Deleting computer with ID {}:", computerId);
        computerRepository.delete(computer.get());
    }
}

