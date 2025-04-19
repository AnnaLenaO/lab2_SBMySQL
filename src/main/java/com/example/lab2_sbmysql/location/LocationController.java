package com.example.lab2_sbmysql.location;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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

    @GetMapping("/locations/public/category/{category}")
    public List<LocationDto> getAllPublicLocationsByCategory(@PathVariable Integer category) {
        return locationService.findPublicByCategory(category);
    }

    //GET all locations within a specific radius

    @PostMapping("/locations")
    public ResponseEntity<Void> createLocation(@RequestBody LocationDto locationDto) {
        int id = locationService.addLocation(locationDto);
        return ResponseEntity.created(URI.create("/locations/" + id)).build();
    }

    @PutMapping("/locations/{id}")
    public ResponseEntity<Void> updateLocation(@PathVariable Integer id, @RequestBody LocationDto locationDto) {
        locationService.updateLocation(id, locationDto);
        return ResponseEntity.noContent().build();
    }
}
