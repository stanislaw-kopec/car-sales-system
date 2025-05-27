package com.example.vehiclemarket.repository;

import com.example.vehiclemarket.Model.FuelType;
import com.example.vehiclemarket.entity.Truck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TruckRepository extends JpaRepository<Truck, Long> {
    List<Truck> findByFuelType(FuelType fuelType);
}
