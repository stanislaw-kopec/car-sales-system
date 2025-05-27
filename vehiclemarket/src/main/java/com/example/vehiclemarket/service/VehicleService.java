package com.example.vehiclemarket.service;

import com.example.vehiclemarket.Model.FuelType;
import com.example.vehiclemarket.entity.Car;
import com.example.vehiclemarket.entity.Motorcycle;
import com.example.vehiclemarket.entity.Truck;
import com.example.vehiclemarket.entity.Vehicle;
import com.example.vehiclemarket.repository.CarRepository;
import com.example.vehiclemarket.repository.MotorcycleRepository;
import com.example.vehiclemarket.repository.TruckRepository;
import com.example.vehiclemarket.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final CarRepository carRepository;
    private final TruckRepository truckRepository;
    private final MotorcycleRepository motorcycleRepository;


    public VehicleService(VehicleRepository vehicleRepository, CarRepository carRepository, TruckRepository truckRepository, MotorcycleRepository motorcycleRepository) {
        this.vehicleRepository = vehicleRepository;
        this.carRepository = carRepository;
        this.truckRepository = truckRepository;
        this.motorcycleRepository = motorcycleRepository;
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Optional<Vehicle> getVehicleById(Long id) {
        return vehicleRepository.findById(id);
    }

    public Vehicle saveVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }

    public Optional<Vehicle> updateTypedVehicle(Long id, Vehicle updatedVehicle) {
        return vehicleRepository.findById(id).map(existing -> {
            existing.setBrand(updatedVehicle.getBrand());
            existing.setModel(updatedVehicle.getModel());
            existing.setYear(updatedVehicle.getYear());
            existing.setMileage(updatedVehicle.getMileage());
            existing.setFuelType(updatedVehicle.getFuelType());
            existing.setTransmission(updatedVehicle.getTransmission());
            existing.setColor(updatedVehicle.getColor());

            if (existing instanceof Car && updatedVehicle instanceof Car) {
                Car existingCar = (Car) existing;
                Car updatedCar = (Car) updatedVehicle;
                existingCar.setDoors(updatedCar.getDoors());
                existingCar.setBodyType(updatedCar.getBodyType());
                existingCar.setEngineCapacity(updatedCar.getEngineCapacity());
            } else if (existing instanceof Truck && updatedVehicle instanceof Truck) {
                Truck existingTruck = (Truck) existing;
                Truck updatedTruck = (Truck) updatedVehicle;
                existingTruck.setMaxLoad(updatedTruck.getMaxLoad());
                existingTruck.setNumberOfAxles(updatedTruck.getNumberOfAxles());
            } else if (existing instanceof Motorcycle && updatedVehicle instanceof Motorcycle) {
                Motorcycle existingMoto = (Motorcycle) existing;
                Motorcycle updatedMoto = (Motorcycle) updatedVehicle;
                existingMoto.setMotoType(updatedMoto.getMotoType());
                existingMoto.setEngineCapacity(updatedMoto.getEngineCapacity());
            }

            return vehicleRepository.save(existing);
        });
    }

    public List<Car> getCarsByFuelType(FuelType fuelType) {
        return carRepository.findByFuelType(fuelType);
    }

    public List<Motorcycle> getMotorcyclesByFuelType(FuelType fuelType) {
        return motorcycleRepository.findByFuelType(fuelType);
    }

    public List<Truck> getTrucksByFuelType(FuelType fuelType) {
        return truckRepository.findByFuelType(fuelType);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public List<Motorcycle> getAllMotorcycles() {
        return motorcycleRepository.findAll();
    }

    public List<Truck> getAllTrucks() {
        return truckRepository.findAll();
    }


}
