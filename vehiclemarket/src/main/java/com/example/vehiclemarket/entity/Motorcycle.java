package com.example.vehiclemarket.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Motorcycle extends Vehicle {
    private String motoType;
    private Integer engineCapacity;
}
