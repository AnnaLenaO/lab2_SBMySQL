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

    public List<LocationDto> allPublicLocations() {
        return locationRepository.findByStatus("public").stream()
                .map(LocationDto::fromLocation)
                .toList();
    }

    public Optional<LocationDto> findPublicByCoordinate(String coordinate) {
        return locationRepository.findByStatusAndCoordinate("public", coordinate)
                .map(LocationDto::fromLocation);
    }

    public List<LocationDto> findPublicByCategory(Integer category) {
        return locationRepository.findByStatusAndCategory_Id("public", category).stream()
                .map(LocationDto::fromLocation)
                .toList();
    }
}
