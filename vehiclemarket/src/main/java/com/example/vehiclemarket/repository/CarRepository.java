package com.example.vehiclemarket.repository;

import com.example.vehiclemarket.Model.FuelType;
import com.example.vehiclemarket.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByFuelType(FuelType fuelType);
}
