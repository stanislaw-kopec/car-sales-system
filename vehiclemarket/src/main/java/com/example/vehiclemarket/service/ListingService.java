package com.example.vehiclemarket.service;

import com.example.vehiclemarket.entity.Listing;
import com.example.vehiclemarket.repository.ListingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ListingService {

    private final ListingRepository listingRepository;

    public ListingService(ListingRepository listingRepository) {
        this.listingRepository = listingRepository;
    }

    public List<Listing> getAllListings() {
        return listingRepository.findAll();
    }

    public List<Listing> getActiveListings() {
        return listingRepository.findByIsActiveTrue();
    }

    public Optional<Listing> getListingById(Long id) {
        return listingRepository.findById(id);
    }

    public Listing createListing(Listing listing) {
        if (listing.getUser() == null || listing.getVehicle() == null) {
            throw new IllegalArgumentException("User and Vehicle must be provided.");
        }
        return listingRepository.save(listing);
    }

    public void deleteListing(Long id) {
        listingRepository.deleteById(id);
    }

    public Optional<Listing> updateListing(Long id, Listing updatedListing) {
        return listingRepository.findById(id).map(existingListing -> {
            existingListing.setTitle(updatedListing.getTitle());
            existingListing.setDescription(updatedListing.getDescription());
            existingListing.setPrice(updatedListing.getPrice());
            existingListing.setIsActive(updatedListing.getIsActive());
            return listingRepository.save(existingListing);
        });
    }

}
