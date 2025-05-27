package com.example.vehiclemarket.entity;

import com.example.vehiclemarket.Model.FuelType;
import com.example.vehiclemarket.Model.TransmissionType;
import com.example.vehiclemarket.Model.VehicleType;
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

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    private String brand;
    private String model;
    private Integer year;
    private Integer mileage;
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;
    @Enumerated(EnumType.STRING)
    private TransmissionType transmission;
    private String color;

    private LocalDateTime createdAt = LocalDateTime.now();
}
