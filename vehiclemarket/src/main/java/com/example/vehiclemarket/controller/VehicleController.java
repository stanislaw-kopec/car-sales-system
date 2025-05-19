package com.example.vehiclemarket.controller;

import com.example.vehiclemarket.entity.Car;
import com.example.vehiclemarket.entity.Motorcycle;
import com.example.vehiclemarket.entity.Truck;
import com.example.vehiclemarket.entity.Vehicle;
import com.example.vehiclemarket.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @GetMapping
    public List<Vehicle> getAllVehicle() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicle(@PathVariable Long id) {
        return vehicleService.getVehicleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/car")
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        return ResponseEntity.ok((Car) vehicleService.saveVehicle(car));
    }

    @PostMapping("/motorcycle")
    public ResponseEntity<Motorcycle> createMotorcycle(@RequestBody Motorcycle motorcycle) {
        return ResponseEntity.ok((Motorcycle) vehicleService.saveVehicle(motorcycle));
    }

    @PostMapping("/truck")
    public ResponseEntity<Truck> createTruck(@RequestBody Truck truck) {
        return ResponseEntity.ok((Truck) vehicleService.saveVehicle(truck));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Vehicle> deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }
}
