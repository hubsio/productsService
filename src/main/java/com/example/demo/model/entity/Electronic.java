package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "electronic")
@PrimaryKeyJoinColumn(name = "electronics_id")
public class Electronic extends Product {
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Accessory> accessories;
}
