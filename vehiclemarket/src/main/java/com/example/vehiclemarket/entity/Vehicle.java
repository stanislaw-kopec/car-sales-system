package com.example.vehiclemarket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vehicleType;

    private String brand;
    private String model;
    private Integer year;
    private Integer mileage;
    private String fuelType;
    private String transmission;
    private String color;

    private LocalDateTime createdAt = LocalDateTime.now();
}
