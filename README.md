# Spring MVC Weather Forecast App

This is a simple Spring Boot MVC web application that retrieves and displays weather forecast data from the National Weather Service (NWS) API using latitude and longitude input. It demonstrates RESTful API integration, Thymeleaf-based views, and clean MVC architecture.

## Features

- User input for latitude and longitude
- Option to autofill coordinates via browser geolocation
- Fetches forecast data from NWS API:
  - `/points/{lat},{lon}`
  - `/gridpoints/{office}/{x},{y}/forecast`
  - `/stations/{id}/observations/latest`
- Displays:
  - Current temperature (converted to °F)
  - Forecasted high and low (overall)
  - Detailed period-by-period forecast
  - Weather icons and descriptions
- Dynamic background based on time of day
- Clean, responsive UI using custom CSS

## Technologies Used

- Java 21
- Spring Boot 3.x
- Thymeleaf
- REST APIs with `RestTemplate`
- HTML5 / CSS3
- NWS Weather API

## How to Run

1. Clone the repository and open in your IDE.
2. Build the project using Maven or your IDE's build tools.
3. Run `WeatherApplication.java`.
4. Open a browser and navigate to `http://localhost:8080`.

## Folder Structure

```
src/
 └── main/
     ├── java/
     │   └── com.example.weatherapp/
     │       ├── controller/
     │       ├── model/
     │       └── service/
     └── resources/
         ├── static/
         │   └── style.css
         └── templates/
             ├── weather_form.html
             └── weather_result.html
```

## Notes

- All code is annotated with in-line comments for maintainability.
- API calls are wrapped in a try/catch block for resilience.
- For real-world deployment, add robust error handling and input validation.

## Contact

Timothy Fleck  
[tfleck78@gmail.com](mailto:tfleck78@gmail.com)  
[https://github.com/Chuca78/](https://github.com/Chuca78/Spring-MVC-Weather-App)
