package com.example.vehiclemarket.entity;

import com.example.vehiclemarket.Model.FuelType;
import com.example.vehiclemarket.Model.TransmissionType;
import com.example.vehiclemarket.Model.VehicleType;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "Unique identifier of the vehicle", example = "101")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Schema(description = "Type of the vehicle (CAR, MOTORCYCLE, TRUCK)", example = "CAR")
    private VehicleType vehicleType;

    @Schema(description = "Brand of the vehicle", example = "Toyota")
    private String brand;
    @Schema(description = "Vehicle model", example = "Corolla")
    private String model;
    @Schema(description = "Year the vehicle was manufactured", example = "2018")
    private Integer year;
    @Schema(description = "Mileage in kilometers", example = "85000")
    private Integer mileage;
    @Schema(description = "Fuel type used by the vehicle", example = "PETROL")
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;
    @Schema(description = "Transmission type of the vehicle", example = "AUTOMATIC")
    @Enumerated(EnumType.STRING)
    private TransmissionType transmission;
    @Schema(description = "Color of the vehicle", example = "Red")
    private String color;

    @Schema(description = "Date and time the vehicle entry was created", example = "2024-05-01T10:15:30")
    private LocalDateTime createdAt = LocalDateTime.now();
}
