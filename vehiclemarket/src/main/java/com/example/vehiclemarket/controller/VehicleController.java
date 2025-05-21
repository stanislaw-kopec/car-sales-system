package com.example.vehiclemarket.controller;

import com.example.vehiclemarket.entity.Car;
import com.example.vehiclemarket.entity.Motorcycle;
import com.example.vehiclemarket.entity.Truck;
import com.example.vehiclemarket.entity.Vehicle;
import com.example.vehiclemarket.service.UserService;
import com.example.vehiclemarket.service.VehicleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

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

    @GetMapping("/cars")
    public List<Car> getCars(@RequestParam(required = false) String fuelType) {
        if (fuelType != null) {
            return vehicleService.getCarsByFuelType(fuelType);
        }
        return vehicleService.getAllCars();
    }

    @GetMapping("/motorcycles")
    public List<Motorcycle> getMotorcycles(@RequestParam(required = false) String fuelType) {
        if (fuelType != null) {
            return vehicleService.getMotorcyclesByFuelType(fuelType);
        }
        return vehicleService.getAllMotorcycles();
    }

    @GetMapping("/trucks")
    public List<Truck> getTrucks(@RequestParam(required = false) String fuelType) {
        if (fuelType != null) {
            return vehicleService.getTrucksByFuelType(fuelType);
        }
        return vehicleService.getAllTrucks();
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

    @PutMapping("/{id}")
    public ResponseEntity<?> updateVehicle(@PathVariable Long id, @RequestBody Map<String, Object> body) {
        String vehicleType = (String) body.get("vehicleType");

        if (vehicleType == null) {
            return ResponseEntity.badRequest().body("vehicleType is required.");
        }

        ObjectMapper mapper = new ObjectMapper();
        Vehicle updatedVehicle;

        try {
            switch (vehicleType.toLowerCase()) {
                case "car":
                    updatedVehicle = mapper.convertValue(body, Car.class);
                    break;
                case "motorcycle":
                    updatedVehicle = mapper.convertValue(body, Motorcycle.class);
                    break;
                case "truck":
                    updatedVehicle = mapper.convertValue(body, Truck.class);
                    break;
                default:
                    return ResponseEntity.badRequest().body("Unsupported vehicleType: " + vehicleType);
            }

            Optional<Vehicle> result = vehicleService.updateTypedVehicle(id, updatedVehicle);
            return result.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid request data: " + e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Vehicle> deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }
}
