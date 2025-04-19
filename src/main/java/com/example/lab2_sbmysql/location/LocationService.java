package com.example.lab2_sbmysql.location;

import org.springframework.stereotype.Service;

import java.util.List;

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
}
