package com.example.lab2_sbmysql.location;

import com.example.lab2_sbmysql.location.entity.Location;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface LocationRepository extends ListCrudRepository<Location, Integer> {
    List<Location> findByDeleted(Boolean deleted);

    List<Location> findByStatusAndDeleted(String status, Boolean deleted);

    List<Location> findByStatusAndCoordinateAndDeleted(String status, String coordinate, Boolean deleted);

    List<Location> findByStatusAndCategory_IdAndDeleted(String status, Integer category, Boolean deleted);

    boolean existsByNameAndCategory_idAndDeleted(String name, Integer category, Boolean deleted);

    @Query("""
SELECT l.status FROM Location l
""")
    List<String> findAllStatusTypes();

    Optional<Location> findByIdAndDeleted(Integer id, Boolean deleted);
}
