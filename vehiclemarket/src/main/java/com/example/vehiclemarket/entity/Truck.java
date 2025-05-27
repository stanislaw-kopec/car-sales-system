package com.example.vehiclemarket.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Truck extends Vehicle {
    @Schema(description = "Maximum load capacity of the truck in kilograms", example = "15000")
    private Integer maxLoad;
    @Schema(description = "Number of axles on the truck", example = "3")
    private Integer numberOfAxles;
}
