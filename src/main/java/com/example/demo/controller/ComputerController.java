package com.example.demo.controller;

import com.example.demo.dtoConfig.EditComputerCommandDTO;
import com.example.demo.model.entity.Computer;
import com.example.demo.model.entity.dto.ComputerDTO;
import com.example.demo.service.ComputerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/computers")
@RequiredArgsConstructor
public class ComputerController {
    private final ComputerService computerService;

    @GetMapping
    public List<ComputerDTO> getAllComputers() {
        return computerService.getAllComputers();
    }

    @GetMapping("/{id}")
    public ComputerDTO getComputerById(@PathVariable Long id) {
        return computerService.getComputerById(id);
    }

    @PostMapping
    public ComputerDTO createComputer(@RequestBody ComputerDTO computerDTO) {
        return computerService.createComputer(computerDTO);
    }

    @PutMapping("/{id}")
    public ComputerDTO updateComputer(@PathVariable Long id, @RequestBody EditComputerCommandDTO editedComputerDTO) {
        return computerService.updateComputer(id, editedComputerDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteComputer(@PathVariable Long id) {
        computerService.deleteComputer(id);
    }
}
