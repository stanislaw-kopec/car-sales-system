package com.example.vehiclemarket.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private Long id;

    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(precision = 10, scale = 2)
    private BigDecimal price;
    private boolean isActive = true;

    @ManyToOne
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @Column(name = "created_at", updatable = false)
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
