package com.example.vehiclemarket.entity;

import jakarta.persistence.Entity;

@Entity
public class Truck extends Vehicle {
    private Integer maxLoad;
    private Integer numberOfAxles;
}
