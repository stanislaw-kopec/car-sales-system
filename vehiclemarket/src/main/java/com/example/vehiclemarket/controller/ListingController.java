package com.example.vehiclemarket.controller;

import com.example.vehiclemarket.entity.Listing;
import com.example.vehiclemarket.repository.ListingRepository;
import com.example.vehiclemarket.repository.UserRepository;
import com.example.vehiclemarket.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/listings")
@RequiredArgsConstructor
public class ListingController {

    private final ListingRepository listingRepository;
    private final UserRepository userRepository;
    private final VehicleRepository vehicleRepository;

    @GetMapping
    public List<Listing> getAllListing() {
        return listingRepository.findAll();
    }

    @GetMapping("/active")
    public List<Listing> getAllActiveListings() {
        return listingRepository.findByIsActiveTrue();
    }

    @PostMapping
    public ResponseEntity<Listing> createListing(@RequestBody Listing listing) {
        if (listing.getUser() == null || listing.getVehicle() == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(listingRepository.save(listing));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Listing> getListing(@PathVariable Long id) {
        return listingRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void deleteListing(@PathVariable Long id) {
        listingRepository.deleteById(id);
    }
}
