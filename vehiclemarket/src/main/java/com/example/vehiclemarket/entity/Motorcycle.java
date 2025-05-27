package com.example.vehiclemarket.entity;

import com.example.vehiclemarket.Model.MotoType;
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
    private MotoType motoType;
    private Integer engineCapacity;
}
