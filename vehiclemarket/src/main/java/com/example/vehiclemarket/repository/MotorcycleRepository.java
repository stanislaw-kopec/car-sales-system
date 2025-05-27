package com.example.vehiclemarket.repository;

import com.example.vehiclemarket.Model.FuelType;
import com.example.vehiclemarket.entity.Motorcycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MotorcycleRepository extends JpaRepository<Motorcycle, Long> {
    List<Motorcycle> findByFuelType(FuelType fuelType);
}
