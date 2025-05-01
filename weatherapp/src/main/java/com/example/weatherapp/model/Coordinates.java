package com.example.weatherapp.model;

/**
 * A simple model class to hold latitude and longitude input from the user.
 * Used for data binding with the weather input form.
 */
public class Coordinates {
    
    // Latitude value entered by the user
    private double latitude;
    // Longitude value entered by the user
    private double longitude;

    // Default constructor required for form binding and frameworks like Spring
    public Coordinates() {}

    // Optional constructor to initialize coordinates directly
    public Coordinates(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getters and Setters
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
