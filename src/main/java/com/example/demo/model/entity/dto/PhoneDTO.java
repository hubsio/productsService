package com.example.demo.model.entity.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDTO extends ProductDTO {
    private String color;
    private int batteryCapacity;
}
