package com.example.demo.dtoConfig;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class EditElectronicCommandDTO {
    private String name;
    private BigDecimal price;
}
