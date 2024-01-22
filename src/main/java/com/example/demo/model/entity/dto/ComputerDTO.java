package com.example.demo.model.entity.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComputerDTO extends ProductDTO {
    private String processor;
    private int ram;
}
