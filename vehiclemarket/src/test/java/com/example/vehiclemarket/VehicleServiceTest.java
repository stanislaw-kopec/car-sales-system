package com.example.vehiclemarket;

import com.example.vehiclemarket.Model.BodyType;
import com.example.vehiclemarket.Model.FuelType;
import com.example.vehiclemarket.Model.MotoType;
import com.example.vehiclemarket.entity.*;
import com.example.vehiclemarket.repository.*;
import com.example.vehiclemarket.service.VehicleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VehicleServiceTest {

    @Mock
    private VehicleRepository vehicleRepository;
    @Mock
    private CarRepository carRepository;
    @Mock
    private TruckRepository truckRepository;
    @Mock
    private MotorcycleRepository motorcycleRepository;

    private VehicleService vehicleService;

    @BeforeEach
    void setUp() {
        vehicleService = new VehicleService(vehicleRepository, carRepository, truckRepository, motorcycleRepository);
    }

    @Test
    @DisplayName("Should return all vehicles")
    void testGetAllVehicles() {
        Vehicle vehicle1 = new Vehicle();
        Vehicle vehicle2 = new Vehicle();
        when(vehicleRepository.findAll()).thenReturn(List.of(vehicle1, vehicle2));

        List<Vehicle> result = vehicleService.getAllVehicles();

        assertEquals(2, result.size());
        verify(vehicleRepository).findAll();
    }

    @Test
    @DisplayName("Should return vehicle by ID")
    void testGetVehicleById() {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(1L);

        when(vehicleRepository.findById(1L)).thenReturn(Optional.of(vehicle));

        Optional<Vehicle> result = vehicleService.getVehicleById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        verify(vehicleRepository).findById(1L);
    }

    @Test
    @DisplayName("Should save a new vehicle")
    void testSaveVehicle() {
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Toyota");

        when(vehicleRepository.save(vehicle)).thenReturn(vehicle);

        Vehicle result = vehicleService.saveVehicle(vehicle);

        assertNotNull(result);
        assertEquals("Toyota", result.getBrand());
        verify(vehicleRepository).save(vehicle);
    }

    @Test
    @DisplayName("Should delete vehicle by ID")
    void testDeleteVehicle() {
        vehicleService.deleteVehicle(5L);
        verify(vehicleRepository).deleteById(5L);
    }

    @Test
    @DisplayName("Should update existing Car")
    void testUpdateTypedVehicle_Car() {
        Car existingCar = new Car();
        existingCar.setId(1L);
        existingCar.setBrand("OldBrand");

        Car updatedCar = new Car();
        updatedCar.setBrand("NewBrand");
        updatedCar.setDoors(4);
        updatedCar.setBodyType(BodyType.valueOf("Sedan".toUpperCase()));
        updatedCar.setEngineCapacity(1998);

        when(vehicleRepository.findById(1L)).thenReturn(Optional.of(existingCar));
        when(vehicleRepository.save(any(Car.class))).thenReturn(existingCar);

        Optional<Vehicle> result = vehicleService.updateTypedVehicle(1L, updatedCar);

        assertTrue(result.isPresent());
        assertEquals("NewBrand", result.get().getBrand());
        verify(vehicleRepository).save(existingCar);
    }

    @Test
    @DisplayName("Should update existing Truck")
    void testUpdateTypedVehicle_Truck() {
        Truck existingTruck = new Truck();
        existingTruck.setId(2L);
        existingTruck.setBrand("OldTruck");

        Truck updatedTruck = new Truck();
        updatedTruck.setBrand("NewTruck");
        updatedTruck.setMaxLoad(10000);
        updatedTruck.setNumberOfAxles(3);

        when(vehicleRepository.findById(2L)).thenReturn(Optional.of(existingTruck));
        when(vehicleRepository.save(any(Truck.class))).thenReturn(existingTruck);

        Optional<Vehicle> result = vehicleService.updateTypedVehicle(2L, updatedTruck);

        assertTrue(result.isPresent());
        assertEquals("NewTruck", result.get().getBrand());
        verify(vehicleRepository).save(existingTruck);
    }

    @Test
    @DisplayName("Should update existing Motorcycle")
    void testUpdateTypedVehicle_Motorcycle() {
        Motorcycle existingMoto = new Motorcycle();
        existingMoto.setId(3L);
        existingMoto.setBrand("OldMoto");

        Motorcycle updatedMoto = new Motorcycle();
        updatedMoto.setBrand("NewMoto");
        updatedMoto.setMotoType(MotoType.valueOf("Sport".toUpperCase()));
        updatedMoto.setEngineCapacity(650);

        when(vehicleRepository.findById(3L)).thenReturn(Optional.of(existingMoto));
        when(vehicleRepository.save(any(Motorcycle.class))).thenReturn(existingMoto);

        Optional<Vehicle> result = vehicleService.updateTypedVehicle(3L, updatedMoto);

        assertTrue(result.isPresent());
        assertEquals("NewMoto", result.get().getBrand());
        verify(vehicleRepository).save(existingMoto);
    }

    @Test
    @DisplayName("Should return cars by fuel type")
    void testGetCarsByFuelType() {
        when(carRepository.findByFuelType(FuelType.PETROL)).thenReturn(List.of(new Car(), new Car()));
        List<Car> cars = vehicleService.getCarsByFuelType(FuelType.PETROL);
        assertEquals(2, cars.size());
        verify(carRepository).findByFuelType(FuelType.PETROL);
    }

    @Test
    @DisplayName("Should return motorcycles by fuel type")
    void testGetMotorcyclesByFuelType() {
        when(motorcycleRepository.findByFuelType(FuelType.DIESEL)).thenReturn(List.of(new Motorcycle()));
        List<Motorcycle> result = vehicleService.getMotorcyclesByFuelType(FuelType.DIESEL);
        assertEquals(1, result.size());
        verify(motorcycleRepository).findByFuelType(FuelType.DIESEL);
    }

    @Test
    @DisplayName("Should return trucks by fuel type")
    void testGetTrucksByFuelType() {
        when(truckRepository.findByFuelType(FuelType.ELECTRIC)).thenReturn(List.of());
        List<Truck> result = vehicleService.getTrucksByFuelType(FuelType.ELECTRIC);
        assertTrue(result.isEmpty());
        verify(truckRepository).findByFuelType(FuelType.ELECTRIC);
    }

    @Test
    @DisplayName("Should return all cars")
    void testGetAllCars() {
        when(carRepository.findAll()).thenReturn(List.of(new Car(), new Car()));
        List<Car> result = vehicleService.getAllCars();
        assertEquals(2, result.size());
        verify(carRepository).findAll();
    }

    @Test
    @DisplayName("Should return all motorcycles")
    void testGetAllMotorcycles() {
        when(motorcycleRepository.findAll()).thenReturn(List.of(new Motorcycle()));
        List<Motorcycle> result = vehicleService.getAllMotorcycles();
        assertEquals(1, result.size());
        verify(motorcycleRepository).findAll();
    }

    @Test
    @DisplayName("Should return all trucks")
    void testGetAllTrucks() {
        when(truckRepository.findAll()).thenReturn(List.of());
        List<Truck> result = vehicleService.getAllTrucks();
        assertTrue(result.isEmpty());
        verify(truckRepository).findAll();
    }

}
