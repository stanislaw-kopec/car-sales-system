package com.example.vehiclemarket.controller;

import com.example.vehiclemarket.Model.FuelType;
import com.example.vehiclemarket.entity.Car;
import com.example.vehiclemarket.entity.Motorcycle;
import com.example.vehiclemarket.entity.Truck;
import com.example.vehiclemarket.entity.Vehicle;
import com.example.vehiclemarket.service.UserService;
import com.example.vehiclemarket.service.VehicleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehicles")
@Tag(name = "Vehicles", description = "Operations for managing different types of vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    @Operation(summary = "Retrieve all vehicles", description = "Returns a list of all vehicles regardless of type")
    public List<Vehicle> getAllVehicle() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve vehicle by ID", description = "Returns a vehicle by its ID")
    public ResponseEntity<Vehicle> getVehicle(
            @Parameter(description = "ID of the vehicle to retrieve", required = true)
            @PathVariable Long id) {
        return vehicleService.getVehicleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cars")
    @Operation(summary = "Retrieve all cars", description = "Returns a list of all cars, optionally filtered by fuel type")
    public List<Car> getCars(
            @Parameter(description = "Optional fuel type to filter cars", required = false)
            @RequestParam(required = false) FuelType fuelType) {
        if (fuelType != null) {
            return vehicleService.getCarsByFuelType(fuelType);
        }
        return vehicleService.getAllCars();
    }

    @GetMapping("/motorcycles")
    @Operation(summary = "Retrieve all motorcycles", description = "Returns a list of all motorcycles, optionally filtered by fuel type")
    public List<Motorcycle> getMotorcycles(
            @Parameter(description = "Optional fuel type to filter motorcycles")
            @RequestParam(required = false) FuelType fuelType) {
        if (fuelType != null) {
            return vehicleService.getMotorcyclesByFuelType(fuelType);
        }
        return vehicleService.getAllMotorcycles();
    }

    @GetMapping("/trucks")
    @Operation(summary = "Retrieve all trucks", description = "Returns a list of all trucks, optionally filtered by fuel type")
    public List<Truck> getTrucks(
            @Parameter(description = "Optional fuel type to filter trucks")
            @RequestParam(required = false) FuelType fuelType) {
        if (fuelType != null) {
            return vehicleService.getTrucksByFuelType(fuelType);
        }
        return vehicleService.getAllTrucks();
    }


    @PostMapping("/car")
    @Operation(summary = "Create a new car", description = "Adds a new car to the database")
    public ResponseEntity<Car> createCar(
            @Parameter(description = "Car object to create", required = true)
            @RequestBody Car car) {
        return ResponseEntity.ok((Car) vehicleService.saveVehicle(car));
    }

    @PostMapping("/motorcycle")
    @Operation(summary = "Create a new motorcycle", description = "Adds a new motorcycle to the database")
    public ResponseEntity<Motorcycle> createMotorcycle(
            @Parameter(description = "Motorcycle object to create", required = true)
            @RequestBody Motorcycle motorcycle) {
        return ResponseEntity.ok((Motorcycle) vehicleService.saveVehicle(motorcycle));
    }

    @PostMapping("/truck")
    @Operation(summary = "Create a new truck", description = "Adds a new truck to the database")
    public ResponseEntity<Truck> createTruck(
            @Parameter(description = "Truck object to create", required = true)
            @RequestBody Truck truck) {
        return ResponseEntity.ok((Truck) vehicleService.saveVehicle(truck));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a vehicle", description = "Updates a vehicle based on ID and type provided in request body")
    public ResponseEntity<?> updateVehicle(
            @Parameter(description = "ID of the vehicle to update", required = true)
            @PathVariable Long id,
            @Parameter(description = "Map containing updated vehicle fields and vehicleType", required = true)
            @RequestBody Map<String, Object> body) {
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
    @Operation(summary = "Delete a vehicle", description = "Deletes a vehicle from the database using its ID")
    public ResponseEntity<Vehicle> deleteVehicle(
            @Parameter(description = "ID of the vehicle to delete", required = true)
            @PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }
}
