package com.example.weatherapp.service;

import com.example.weatherapp.model.ForecastPeriod;
import com.example.weatherapp.model.WeatherReport;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Service class responsible for retrieving and processing weather data
 * from the National Weather Service (NWS) API.
 */
@Service
public class WeatherService {

    // Used to perform HTTP requests to the external API
    private final RestTemplate restTemplate = new RestTemplate();

    // Retrieves weather forecast and current conditions based on latitude and longitude.
    @SuppressWarnings("unchecked")
    public WeatherReport getWeatherForecast(double latitude, double longitude) {
        List<ForecastPeriod> forecastList = new ArrayList<>();
        Double currentTempFahrenheit = null;

        try {
            // Step 1: Get grid location using lat/lon
            String pointsUrl = "https://api.weather.gov/points/" + latitude + "," + longitude;
            Map<String, Object> pointsResponse = restTemplate.getForObject(pointsUrl, Map.class);

            if (pointsResponse != null && pointsResponse.containsKey("properties")) {
                Map<String, Object> properties = (Map<String, Object>) pointsResponse.get("properties");

                // Extract office and grid coordinates
                String office = (String) properties.get("gridId");
                int gridX = (int) properties.get("gridX");
                int gridY = (int) properties.get("gridY");

                // Step 2: Retrieve forecast using grid coordinates
                String forecastUrl = "https://api.weather.gov/gridpoints/" + office + "/" + gridX + "," + gridY + "/forecast";
                Map<String, Object> forecastResponse = restTemplate.getForObject(forecastUrl, Map.class);

                if (forecastResponse != null && forecastResponse.containsKey("properties")) {
                    Map<String, Object> forecastProperties = (Map<String, Object>) forecastResponse.get("properties");
                    List<Map<String, Object>> periods = (List<Map<String, Object>>) forecastProperties.get("periods");

                    // Iterate through each forecast period and extract weather data
                    for (Map<String, Object> period : periods) {
                        String name = (String) period.get("name");
                        int temperature = (int) period.get("temperature");
                        String temperatureUnit = (String) period.get("temperatureUnit");
                        String shortForecast = (String) period.get("shortForecast");
                        String detailedForecast = (String) period.get("detailedForecast");
                        String icon = (String) period.get("icon");
                        boolean isDaytime = (boolean) period.get("isDaytime");
                        

                        // Extract probability of precipitation if available
                        Map<String, Object> precip = (Map<String, Object>) period.get("probabilityOfPrecipitation");
                        Integer precipitation = (precip != null && precip.get("value") != null)
                            ? ((Number) precip.get("value")).intValue()
                            : null;

                        // Create a ForecastPeriod object and add it to the list
                        forecastList.add(new ForecastPeriod(
                            name,
                            temperature,
                            temperatureUnit,
                            shortForecast,
                            detailedForecast,
                            icon,
                            precipitation,
                            isDaytime
                        ));
                    }
                }

                // Step 3: Get observation station list for this location
                String stationsUrl = (String) properties.get("observationStations");
                Map<String, Object> stationResponse = restTemplate.getForObject(stationsUrl, Map.class);

                if (stationResponse != null && stationResponse.containsKey("features")) {
                    List<Map<String, Object>> features = (List<Map<String, Object>>) stationResponse.get("features");

                    if (!features.isEmpty()) {
                        // Use the first station in the list
                        Map<String, Object> firstFeature = features.get(0);
                        Map<String, Object> stationProps = (Map<String, Object>) firstFeature.get("properties");
                        String stationId = (String) stationProps.get("stationIdentifier");

                        // Step 4: Get current observation from the selected station
                        String obsUrl = "https://api.weather.gov/stations/" + stationId + "/observations/latest";
                        Map<String, Object> obsResponse = restTemplate.getForObject(obsUrl, Map.class);

                        if (obsResponse != null && obsResponse.containsKey("properties")) {
                            Map<String, Object> obsProps = (Map<String, Object>) obsResponse.get("properties");
                            Map<String, Object> tempMap = (Map<String, Object>) obsProps.get("temperature");

                            // Convert temperature from Celsius to Fahrenheit
                            if (tempMap != null && tempMap.get("value") != null) {
                                double tempC = ((Number) tempMap.get("value")).doubleValue();
                                currentTempFahrenheit = (tempC * 9 / 5) + 32;
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            // Print stack trace for debugging; in production, consider using a logger
            e.printStackTrace();
        }

        // Return a WeatherReport containing both forecast data and current temp
        return new WeatherReport(forecastList, currentTempFahrenheit);
    }
}
