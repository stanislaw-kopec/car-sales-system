package com.example.vehiclemarket.entity;

import jakarta.persistence.Entity;

@Entity
public class Motorcycle extends Vehicle {
    private String motoType;
    private Integer engineCapacity;
}
