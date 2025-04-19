package com.example.lab2_sbmysql.location;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
