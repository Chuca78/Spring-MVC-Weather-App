package com.example.weatherapp;

// Import Spring Boot core classes
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Marks this class as the entry point for the Spring Boot application
@SpringBootApplication
public class WeatherApplication {

    // Main method: bootstraps the Spring Boot application
	public static void main(String[] args) {
		SpringApplication.run(WeatherApplication.class, args);
	}

}
