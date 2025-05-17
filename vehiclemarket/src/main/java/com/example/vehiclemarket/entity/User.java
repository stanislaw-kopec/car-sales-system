package com.example.vehiclemarket.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String passwordHash;
    private String name;

    @OneToMany(mappedBy = "user")
    private List<Listing> listings = new ArrayList<>();
}
