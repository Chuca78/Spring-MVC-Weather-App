
package com.example.weatherapp.controller;

// Import model classes
import com.example.weatherapp.model.Coordinates;
import com.example.weatherapp.model.ForecastPeriod;
import com.example.weatherapp.model.WeatherReport;

// Import service layer
import com.example.weatherapp.service.WeatherService;

// Spring Framework annotations and web components
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Comparator;
import java.util.List;

// Marks this class as a Spring MVC controller
@Controller
public class WeatherController {

    // Injects the WeatherService dependency
    @Autowired
    private WeatherService weatherService;

    // Handles GET request to root URL, displaying the form for user input
    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("coords", new Coordinates());
        return "weather_form";
    }

    // Handles POST request after form submission
    @PostMapping("/forecast")
    public String getForecast(@ModelAttribute Coordinates coords, Model model) {
        // Call service to retrieve weather data from NWS API
        WeatherReport report = weatherService.getWeatherForecast(
                coords.getLatitude(), coords.getLongitude());

        // Extract forecast list and current temperature      
        List<ForecastPeriod> forecast = report.getForecast();
        model.addAttribute("forecast", forecast);
        model.addAttribute("currentTemp", report.getCurrentTemperature());

        // Compute overall high temperature from forecast periods
        Integer highTemp = forecast.stream()
                .map(ForecastPeriod::getTemperature)
                .filter(temp -> temp != null)
                .max(Comparator.naturalOrder())
                .orElse(null);

        // Compute overall low temperature from forecast periods
        Integer lowTemp = forecast.stream()
                .map(ForecastPeriod::getTemperature)
                .filter(temp -> temp != null)
                .min(Comparator.naturalOrder())
                .orElse(null);

        // Add summary high/low values to the model
        model.addAttribute("highTemp", highTemp);
        model.addAttribute("lowTemp", lowTemp);

        // Return the view to display forecast results
        return "weather_result";
    }
}
