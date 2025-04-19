package com.example.lab2_sbmysql.location;

import com.example.lab2_sbmysql.location.entity.Location;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface LocationRepository extends ListCrudRepository<Location, Integer> {
    Optional<Location> findByCoordinateAndStatus(String coordinate, String status);

    List<Location> findByStatus(String status);
}
