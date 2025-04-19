package com.example.lab2_sbmysql.location;

import com.example.lab2_sbmysql.location.entity.Location;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface LocationRepository extends ListCrudRepository<Location, Integer> {
    List<Location> findByStatus(String status);

    Optional<Location> findByStatusAndCoordinate(String status, String coordinate);

    List<Location> findByStatusAndCategory_Id(String status, Integer category);

    boolean existsByNameAndCategory_id(String name, Integer category);
}
