package com.example.vehiclemarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.example.vehiclemarket.entity")
@EnableJpaRepositories("com.example.vehiclemarket.repository")
public class VehicleMarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehicleMarketApplication.class, args);
	}

}
