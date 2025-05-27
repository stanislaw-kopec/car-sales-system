package com.example.vehiclemarket;

import com.example.vehiclemarket.entity.Listing;
import com.example.vehiclemarket.entity.User;
import com.example.vehiclemarket.entity.Vehicle;
import com.example.vehiclemarket.repository.ListingRepository;
import com.example.vehiclemarket.service.ListingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ListingServiceTest {
    @Mock
    private ListingRepository listingRepository;

    private ListingService listingService;

    @BeforeEach
    void setUp() {
        listingService = new ListingService(listingRepository);
    }

    @Test
    @DisplayName("Should return all listings")
    void testGetAllListings() {
        Listing l1 = new Listing();
        Listing l2 = new Listing();

        when(listingRepository.findAll()).thenReturn(List.of(l1, l2));

        List<Listing> listings = listingService.getAllListings();

        assertEquals(2, listings.size());
        verify(listingRepository).findAll();
    }

    @Test
    @DisplayName("Should return only active listings")
    void testGetActiveListings() {
        Listing l1 = new Listing();
        l1.setIsActive(true);

        when(listingRepository.findByIsActiveTrue()).thenReturn(List.of(l1));

        List<Listing> activeListings = listingService.getActiveListings();

        assertEquals(1, activeListings.size());
        assertTrue(activeListings.get(0).getIsActive());
        verify(listingRepository).findByIsActiveTrue();
    }

    @Test
    @DisplayName("Should return listing by ID")
    void testGetListingById() {
        Listing listing = new Listing();
        listing.setId(1L);

        when(listingRepository.findById(1L)).thenReturn(Optional.of(listing));

        Optional<Listing> result = listingService.getListingById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        verify(listingRepository).findById(1L);
    }

    @Test
    @DisplayName("Should create listing with valid user and vehicle")
    void testCreateListing_Valid() {
        Listing listing = new Listing();
        listing.setUser(new User());
        listing.setVehicle(new Vehicle());

        when(listingRepository.save(listing)).thenReturn(listing);

        Listing created = listingService.createListing(listing);

        assertNotNull(created);
        verify(listingRepository).save(listing);
    }

    @Test
    @DisplayName("Should throw exception if user is missing")
    void testCreateListing_NoUser() {
        Listing listing = new Listing();
        listing.setVehicle(new Vehicle());

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                listingService.createListing(listing)
        );

        assertEquals("User and Vehicle must be provided.", exception.getMessage());
        verify(listingRepository, never()).save(any());
    }

    @Test
    @DisplayName("Should throw exception if vehicle is missing")
    void testCreateListing_NoVehicle() {
        Listing listing = new Listing();
        listing.setUser(new User());

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                listingService.createListing(listing)
        );

        assertEquals("User and Vehicle must be provided.", exception.getMessage());
        verify(listingRepository, never()).save(any());
    }

    @Test
    @DisplayName("Should delete listing by ID")
    void testDeleteListing() {
        listingService.deleteListing(99L);
        verify(listingRepository).deleteById(99L);
    }

    @Test
    @DisplayName("Should update existing listing")
    void testUpdateListing() {
        Listing existing = new Listing();
        existing.setId(1L);
        existing.setTitle("Old Title");
        existing.setIsActive(true);

        Listing updated = new Listing();
        updated.setTitle("New Title");
        updated.setDescription("Updated desc");
        updated.setPrice(BigDecimal.valueOf(9999));
        updated.setIsActive(false);

        when(listingRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(listingRepository.save(existing)).thenReturn(existing);

        Optional<Listing> result = listingService.updateListing(1L, updated);

        assertTrue(result.isPresent());
        assertEquals("New Title", result.get().getTitle());
        assertEquals("Updated desc", result.get().getDescription());
        assertEquals(BigDecimal.valueOf(9999), result.get().getPrice());
        assertFalse(result.get().getIsActive());
        verify(listingRepository).save(existing);
    }
}
