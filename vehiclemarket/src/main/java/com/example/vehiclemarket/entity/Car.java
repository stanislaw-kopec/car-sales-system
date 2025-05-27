package com.example.vehiclemarket.entity;

import com.example.vehiclemarket.Model.BodyType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Car extends Vehicle{
    private Integer doors;
    @Enumerated(EnumType.STRING)
    private BodyType bodyType;
    private Integer engineCapacity;
}
