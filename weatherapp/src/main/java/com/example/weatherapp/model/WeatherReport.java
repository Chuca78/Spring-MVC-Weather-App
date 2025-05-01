package com.example.weatherapp.model;

import java.util.List;

/**
 * A container class that holds the full weather data result
 * including the forecast periods and the current temperature.
 */
public class WeatherReport {

    // List of forecast periods retrieved from the NWS API
    private List<ForecastPeriod> forecast;
    // Most recent observed temperature (in Fahrenheit)
    private Double currentTemperature;

    // Default constructor required for frameworks and deserialization
    public WeatherReport() {}

    // Full constructor to initialize both fields
    public WeatherReport(List<ForecastPeriod> forecast, Double currentTemperature) {
        this.forecast = forecast;
        this.currentTemperature = currentTemperature;
    }

    // Getter for the forecast periods list
    public List<ForecastPeriod> getForecast() {
        return forecast;
    }

    // Setter for the forecast list
    public void setForecast(List<ForecastPeriod> forecast) {
        this.forecast = forecast;
    }

    // Getter for the current temperature
    public Double getCurrentTemperature() {
        return currentTemperature;
    }

    // Setter for the current temperature
    public void setCurrentTemperature(Double currentTemperature) {
        this.currentTemperature = currentTemperature;
    }
}
