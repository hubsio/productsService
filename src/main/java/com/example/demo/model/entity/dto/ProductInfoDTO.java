package com.example.demo.model.entity.dto;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductInfoDTO {
    private Long id;
    private String name;
    private BigDecimal price;
}
