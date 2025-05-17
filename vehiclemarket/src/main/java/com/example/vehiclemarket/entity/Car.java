package com.example.vehiclemarket.entity;

import jakarta.persistence.Entity;

@Entity
public class Car extends Vehicle{
    private Integer doors;
    private String bodyType;
    private Integer engineCapacity;
}
