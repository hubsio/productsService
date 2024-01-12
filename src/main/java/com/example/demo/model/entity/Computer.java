package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "computer")
@PrimaryKeyJoinColumn(name = "computer_id")
public class Computer extends Product {
    private String processor;
    private int ram;
    @Column(name = "additional_accessories")
    private String additionalAccessories;
}
