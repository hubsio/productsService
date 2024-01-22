package com.example.demo.service;

import com.example.demo.dtoConfig.EditComputerCommandDTO;
import com.example.demo.exception.DuplicateEntityException;
import com.example.demo.exception.InvalidDataException;
import com.example.demo.mapper.ComputerMapper;
import com.example.demo.model.entity.Computer;
import com.example.demo.model.entity.dto.ComputerDTO;
import com.example.demo.repository.ComputerRepository;
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
public class ComputerService {
    private final ComputerRepository computerRepository;
    private final ComputerMapper computerMapper;

    public List<ComputerDTO> getAllComputers() {
        List<Computer> computers = computerRepository.findAll();
        return computers.stream()
                .map(computerMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ComputerDTO getComputerById(Long computerId) {
        Computer computer = computerRepository.findById(computerId)
                .orElseThrow(() -> new EntityNotFoundException("Computer not found"));
        return computerMapper.toDTO(computer);
    }

    @Transactional
    public ComputerDTO createComputer(ComputerDTO computerDTO) {
        if (computerDTO.getId() != null && computerRepository.existsById(computerDTO.getId())) {
            throw new DuplicateEntityException("Computer with this ID already exists");
        }

        Computer computer = computerMapper.toEntity(computerDTO);
        log.info("Creating a new computer: {}", computer);
        Computer savedComputer = computerRepository.save(computer);
        return computerMapper.toDTO(savedComputer);
    }

    @Transactional
    public ComputerDTO updateComputer(Long computerId, EditComputerCommandDTO editedComputerDTO) {
        log.info("Editing computer with ID: {}", computerId);
        Computer existingComputer = computerRepository.findById(computerId)
                .orElseThrow(() -> new EntityNotFoundException("Computer with given ID not found"));

        if (editedComputerDTO.getName() == null ||
                editedComputerDTO.getPrice() == null) {
            throw new InvalidDataException("Computer data cannot be null");
        }

        existingComputer.setName(editedComputerDTO.getName());
        existingComputer.setPrice(editedComputerDTO.getPrice());
        existingComputer.setProcessor(editedComputerDTO.getProcessor());
        existingComputer.setRam(editedComputerDTO.getRam());

        computerRepository.save(existingComputer);
        log.info("Computer with ID {} edited successfully", computerId);
        return computerMapper.toDTO(existingComputer);
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

