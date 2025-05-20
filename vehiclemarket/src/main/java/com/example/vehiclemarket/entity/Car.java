package com.example.vehiclemarket.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Car extends Vehicle{
    private Integer doors;
    private String bodyType;
    private Integer engineCapacity;
}
