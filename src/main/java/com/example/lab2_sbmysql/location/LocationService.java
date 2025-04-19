package com.example.lab2_sbmysql.location;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {
    LocationRepository locationRepository;
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<LocationDto> allLocations() {
        return locationRepository.findAll().stream()
                .map(LocationDto::fromLocation)
                .toList();
    }

    public Optional<LocationDto> findByCoordinate(String coordinate) {
        return locationRepository.findByCoordinateAndStatus(coordinate, "public")
                .map(LocationDto::fromLocation);
    }
}
