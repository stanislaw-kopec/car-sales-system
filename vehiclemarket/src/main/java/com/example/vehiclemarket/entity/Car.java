package com.example.vehiclemarket.entity;

import com.example.vehiclemarket.Model.BodyType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Car extends Vehicle{
    @Schema(description = "Number of doors in the car", example = "5")
    private Integer doors;
    @Schema(description = "Body type of the car (e.g., SEDAN, SUV)", example = "SEDAN")
    @Enumerated(EnumType.STRING)
    private BodyType bodyType;
    @Schema(description = "Engine capacity in cubic centimeters (cc)", example = "1600")
    private Integer engineCapacity;
}
