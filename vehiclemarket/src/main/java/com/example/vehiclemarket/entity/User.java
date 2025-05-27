package com.example.vehiclemarket.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the user", example = "1")
    private Long id;

    @Schema(description = "Email address of the user", example = "john.doe@example.com")
    private String email;
    @Schema(description = "Hashed password of the user (not visible to clients)", example = "$2a$10$...")
    private String passwordHash;
    @Schema(description = "Full name of the user", example = "John Doe")
    private String name;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    @Schema(description = "List of listings created by this user")
    private List<Listing> listings = new ArrayList<>();
}
