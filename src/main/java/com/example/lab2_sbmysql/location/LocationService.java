package com.example.lab2_sbmysql.location;

import com.example.lab2_sbmysql.category.CategoryRepository;
import com.example.lab2_sbmysql.category.entity.Category;
import com.example.lab2_sbmysql.location.entity.Location;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.geolatte.geom.crs.CoordinateReferenceSystems.WGS84;

@Service
public class LocationService {
    private final CategoryRepository categoryRepository;
    LocationRepository locationRepository;
    public LocationService(LocationRepository locationRepository, CategoryRepository categoryRepository) {
        this.locationRepository = locationRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<LocationDto> allLocations() {
        return locationRepository.findByDeleted(false).stream()
                .map(LocationDto::fromLocation)
                .toList();
    }

    public List<LocationDto> allPublicLocations() {
        return locationRepository.findByStatusAndDeleted("public", false).stream()
                .map(LocationDto::fromLocation)
                .toList();
    }

    public List<LocationDto> findPublicByCoordinate(Point<G2D> coordinate) {
        return locationRepository.findByStatusAndCoordinateAndDeleted("public", coordinate, false).stream()
                .map(LocationDto::fromLocation)
                .toList();
    }

    public List<LocationDto> findPublicByCategory(Integer category) {
        return locationRepository.findByStatusAndCategory_IdAndDeleted("public", category, false).stream()
                .map(LocationDto::fromLocation)
                .toList();
    }

    public int addLocation(LocationDto locationDto) {
        Category category = categoryRepository.findById(locationDto.category())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Category not found"));

        if(locationRepository.existsByNameAndCategory_idAndDeleted(locationDto.name(), category.getId(), false)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Location name already exists for category " + category.getName());
        }

        if(!locationRepository.findAllStatusTypes().contains(locationDto.status())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Invalid Status. Status must be one of " + locationDto.status());
        }

        Point<G2D> geo = new Point<>(new G2D(locationDto.longitude(), locationDto.latitude()), WGS84);

        Location location = new Location();
        location.setName(locationDto.name());
        location.setPersonId(locationDto.person_id());
        location.setStatus(locationDto.status());
        location.setDescription(locationDto.description());
        location.setCoordinate(geo);
        location.setCategory(category);
        return locationRepository.save(location).getId();
    }

    public void updateLocation(Integer id, LocationDto locationDto) {
        Location location = locationRepository.findByIdAndDeleted(id, false)
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

    public void softDeleteLocation(Integer id) {
        Location location = locationRepository.findByIdAndDeleted(id, false)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Location not found"));

        location.setDeleted(true);
        locationRepository.save(location);
    }
}
