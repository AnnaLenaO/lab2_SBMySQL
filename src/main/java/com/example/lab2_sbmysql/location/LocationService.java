package com.example.lab2_sbmysql.location;

import com.example.lab2_sbmysql.category.CategoryRepository;
import com.example.lab2_sbmysql.category.entity.Category;
import com.example.lab2_sbmysql.location.entity.Location;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {
    private final CategoryRepository categoryRepository;
    LocationRepository locationRepository;
    public LocationService(LocationRepository locationRepository, CategoryRepository categoryRepository) {
        this.locationRepository = locationRepository;
        this.categoryRepository = categoryRepository;
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

    public int addLocation(LocationDto locationDto) {
        Category category = categoryRepository.findById(locationDto.category())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Category not found"));

        if(locationRepository.existsByNameAndCategory_id(locationDto.name(), category.getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Location name already exists for category " + category.getName());
        }

        if(!locationRepository.findAllStatusTypes().contains(locationDto.status())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Invalid Status. Status must be one of " + locationDto.status());
        }

        Location location = new Location();
        location.setName(locationDto.name());
        location.setPersonId(locationDto.person_id());
        location.setStatus(locationDto.status());
        location.setDescription(locationDto.description());
        location.setCoordinate(locationDto.coordinate());
        location.setCategory(category);
        return locationRepository.save(location).getId();
    }

    public void updateLocation(Integer id, LocationDto locationDto) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Location not found"));

        if (locationDto.name() != null && !locationDto.name().isEmpty()) {
            location.setName(locationDto.name());
        }
        if (locationDto.status() != null && !locationDto.status().isEmpty()) {
            if(!locationRepository.findAllStatusTypes().contains(locationDto.status())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Invalid Status. Status must be one of " + locationDto.status());
            }
            location.setStatus(locationDto.status());
        }
        if (locationDto.description() != null && !locationDto.description().isEmpty()) {
            location.setDescription(locationDto.description());
        }
        if (locationDto.category() != null) {
            Category category = categoryRepository.findById(locationDto.category())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "Category not found"));
            location.setCategory(category);
        }
        locationRepository.save(location);
    }
}
