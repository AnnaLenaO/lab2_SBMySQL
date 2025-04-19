package com.example.lab2_sbmysql.location;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class LocationController {
    LocationService locationService;
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/locations")
    public List<LocationDto> getAllLocations() {
        return locationService.allLocations();
    }

    @GetMapping("/locations/public")
    public List<LocationDto> getAllLPublicLocations() {
        return locationService.allPublicLocations();
    }

    @GetMapping("/locations/public/{coordinate}")
    public Optional<LocationDto> getPublicLocationByCoordinate(@PathVariable String coordinate) {
        return locationService.findPublicByCoordinate(coordinate);
    }

    @GetMapping("/locations/public/categories/{category}")
    public List<LocationDto> getAllPublicLocationsByCategory(@PathVariable Integer category) {
        return locationService.findPublicByCategory(category);
    }
}
