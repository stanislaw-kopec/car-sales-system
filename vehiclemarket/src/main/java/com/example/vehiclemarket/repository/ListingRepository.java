package com.example.vehiclemarket.repository;

import com.example.vehiclemarket.entity.Listing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListingRepository extends JpaRepository<Listing, Long> {
    List<Listing> findByUserId(Long userId);
    List<Listing> findByIsActiveTrue();
}
