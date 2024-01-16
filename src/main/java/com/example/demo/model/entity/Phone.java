package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "phone")
@PrimaryKeyJoinColumn(name = "phone_id")
public class Phone extends Product {
    private String color;
    private int batteryCapacity;
}
