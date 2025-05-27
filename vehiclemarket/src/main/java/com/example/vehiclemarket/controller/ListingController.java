package com.example.vehiclemarket.controller;

import com.example.vehiclemarket.entity.Listing;
import com.example.vehiclemarket.service.ListingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/listings")
@Tag(name = "Listings", description = "Operations for managing vehicle listings")
public class ListingController {

    private final ListingService listingService;

    ListingController(ListingService listingService) {
        this.listingService = listingService;
    }

    @GetMapping
    @Operation(summary = "Retrieve all listings", description = "Returns a list of all listings")
    public List<Listing> getAllListing() {
        return listingService.getAllListings();
    }

    @GetMapping("/active")
    @Operation(summary = "Retrieve all active listings", description = "Returns a list of all active listings")
    public List<Listing> getAllActiveListings() {
        return listingService.getActiveListings();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retrieve listing by ID", description = "Returns a specific listing by its ID")
    public ResponseEntity<Listing> getListing(
            @Parameter(description = "ID of the listing to retrieve", required = true)
            @PathVariable Long id) {
        return listingService.getListingById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create a new listing", description = "Adds a new vehicle listing")
    public ResponseEntity<Listing> createListing(
            @Parameter(description = "Listing object to create", required = true)
            @RequestBody Listing listing) {
        try {
            Listing saved = listingService.createListing(listing);
            return ResponseEntity.ok(saved);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update listing", description = "Updates an existing listing by its ID")
    public ResponseEntity<Listing> updateListing(
            @Parameter(description = "ID of the listing to update", required = true)
            @PathVariable Long id,
            @Parameter(description = "Updated listing object", required = true)
            @RequestBody Listing updatedListing) {
        return listingService.updateListing(id, updatedListing)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete listing", description = "Deletes a listing by its ID")
    public ResponseEntity<Void> deleteListing(
            @Parameter(description = "ID of the listing to delete", required = true)
            @PathVariable Long id) {
        listingService.deleteListing(id);
        return ResponseEntity.noContent().build();
    }
}
