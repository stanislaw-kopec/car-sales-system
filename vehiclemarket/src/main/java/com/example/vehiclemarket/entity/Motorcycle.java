package com.example.vehiclemarket.entity;

import com.example.vehiclemarket.Model.MotoType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Motorcycle extends Vehicle {
    @Enumerated(EnumType.STRING)
    @Schema(description = "Type of motorcycle (e.g., SPORT, CRUISER)", example = "SPORT")
    private MotoType motoType;
    @Schema(description = "Engine capacity in cubic centimeters (cc)", example = "600")
    private Integer engineCapacity;
}
