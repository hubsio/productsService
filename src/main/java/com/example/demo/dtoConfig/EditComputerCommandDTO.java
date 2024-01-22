package com.example.demo.dtoConfig;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class EditComputerCommandDTO {
    private String name;
    private BigDecimal price;
    private String processor;
    private int ram;
}
