package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "electronic")
@PrimaryKeyJoinColumn(name = "electronics_id")
public class Electronic extends Product {
    @Column(name = "additional_accessories")
    private String additionalAccessories;
}
