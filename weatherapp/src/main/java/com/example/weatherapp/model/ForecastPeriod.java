package com.example.weatherapp.model;


/**
 * Represents a single period in the weather forecast.
 * Includes temperature, description, icon, and other metadata for display.
 */
public class ForecastPeriod {

    private String name;                         // e.g., "Monday", "Monday Night"
    private int temperature;                     // Temperature value for this period
    private String temperatureUnit;              // e.g., "F"
    private String shortForecast;                // Brief description, e.g., "Sunny"
    private String detailedForecast;             // Full forecast text
    private String icon;                         // URL to weather icon
    private Integer probabilityOfPrecipitation;  // Precipitation chance as a percentage
    private boolean isDaytime;                   // True if the period is daytime

    // Default constructor required for frameworks like Spring and Thymeleaf
    public ForecastPeriod() {}

    // Full constructor with all fields
    public ForecastPeriod(String name, int temperature, String temperatureUnit, String shortForecast, String detailedForecast, String icon, Integer probabilityOfPrecipitation, boolean isDaytime) {
    this.name = name;
    this.temperature = temperature;
    this.temperatureUnit = temperatureUnit;
    this.shortForecast = shortForecast;
    this.detailedForecast = detailedForecast;
    this.icon = icon;
    this.probabilityOfPrecipitation = probabilityOfPrecipitation;
    this.isDaytime = isDaytime;
}


    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public String getTemperatureUnit() {
        return temperatureUnit;
    }

    public void setTemperatureUnit(String temperatureUnit) {
        this.temperatureUnit = temperatureUnit;
    }

    public String getShortForecast() {
        return shortForecast;
    }

    public void setShortForecast(String shortForecast) {
        this.shortForecast = shortForecast;
    }

    public String getDetailedForecast() {
        return detailedForecast;
    }

    public void setDetailedForecast(String detailedForecast) {
        this.detailedForecast = detailedForecast;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getProbabilityOfPrecipitation() {
        return probabilityOfPrecipitation;
    }
    
    public void setProbabilityOfPrecipitation(Integer probabilityOfPrecipitation) {
        this.probabilityOfPrecipitation = probabilityOfPrecipitation;
    }

    public boolean isDaytime() {
        return isDaytime;
    }

    public void setIsDaytime(boolean isDaytime) {
        this.isDaytime = isDaytime;
    }

}
