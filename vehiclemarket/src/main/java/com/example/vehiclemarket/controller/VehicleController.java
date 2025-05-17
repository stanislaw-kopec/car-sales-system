package com.example.vehiclemarket.controller;

import com.example.vehiclemarket.entity.Car;
import com.example.vehiclemarket.entity.Motorcycle;
import com.example.vehiclemarket.entity.Truck;
import com.example.vehiclemarket.entity.Vehicle;
import com.example.vehiclemarket.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleRepository vehicleRepository;

    @GetMapping
    public List<Vehicle> getAllVehicle() {
        return vehicleRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicle(@PathVariable Long id) {
        return vehicleRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/car")
    public Car addCar(@RequestBody Car car) {
        return (Car) vehicleRepository.save(car);
    }

    @PostMapping("/motorcycle")
    public Motorcycle addMotorcycle(@RequestBody Motorcycle motorcycle) {
        return (Motorcycle) vehicleRepository.save(motorcycle);
    }

    @PostMapping("/truck")
    public Truck addTruck(@RequestBody Truck truck) {
        return (Truck) vehicleRepository.save(truck);
    }
}
