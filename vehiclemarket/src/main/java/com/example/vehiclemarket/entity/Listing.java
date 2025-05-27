package com.example.vehiclemarket.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Listing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the listing", example = "2001")
    private Long id;

    @Schema(description = "Title of the listing", example = "Toyota Corolla 2018 - Low Mileage")
    private String title;
    @Column(columnDefinition = "TEXT")
    @Schema(description = "Detailed description of the listing", example = "One owner, accident-free, regularly serviced.")
    private String description;
    @Column(precision = 10, scale = 2)
    @Schema(description = "Price of the vehicle in the listing", example = "15000.00")
    private BigDecimal price;
    @Schema(description = "Indicates if the listing is currently active", example = "true")
    private boolean isActive = true;

    @ManyToOne
    @JsonBackReference
    @Schema(description = "User who created the listing")
    private User user;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    @Schema(description = "Vehicle associated with the listing")
    private Vehicle vehicle;

    @Column(name = "created_at", updatable = false)
    @Schema(description = "Date and time when the listing was created", example = "2024-05-01T10:15:30")
    private LocalDateTime createdAt = LocalDateTime.now();


    public Object getIsActive() {
        return isActive;
    }

    public void setIsActive(Object isActive) {
        if (isActive instanceof Boolean) {
            this.isActive = (Boolean) isActive;
        } else {
            throw new IllegalArgumentException("Invalid type for isActive");
        }
    }
}
