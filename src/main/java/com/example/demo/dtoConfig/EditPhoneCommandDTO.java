package com.example.demo.dtoConfig;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class EditPhoneCommandDTO {
    private String name;
    private BigDecimal price;
    private String color;
    private int batteryCapacity;
}
