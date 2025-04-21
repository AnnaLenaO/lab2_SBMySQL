package com.example.lab2_sbmysql.location;

import com.example.lab2_sbmysql.location.entity.Location;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LocationRepository extends ListCrudRepository<Location, Integer> {
    List<Location> findByDeleted(Boolean deleted);

    List<Location> findByStatusAndDeleted(String status, Boolean deleted);

    List<Location> findByStatusAndCategory_IdAndDeleted(String status, Integer category, Boolean deleted);

    boolean existsByNameAndCategory_idAndDeleted(String name, Integer category, Boolean deleted);

    Optional<Location> findByIdAndDeleted(Integer id, Boolean deleted);

    @Query("""
    SELECT l FROM Location l
    WHERE l.status= :status
        AND l.deleted= :deleted
        AND (ST_Equals(ST_GeomFromText(CONCAT('POINT(', :longitude, ' ', :latitude, ')'), 4326),
        l.coordinate)
        = true)
    """)
    List<Location> findByStatusAndCoordinateAndDeleted(
            @Param("status") String status,
            @Param("longitude") Float longitude,
            @Param("latitude") Float latitude,
            @Param("deleted") Boolean deleted);

    @Query("""
    SELECT l.status FROM Location l
    """)
    List<String> findAllStatusTypes();

    @Query(value = """
    SELECT l FROM Location l
    WHERE (ST_Distance_Sphere((
    ST_GeomFromText(CONCAT('POINT(', :longitude, ' ', :latitude, ')'), 4326)),
    l.coordinate )
    <= :distance)
    """)
    List<Location> findWithinDistance(
            @Param("longitude") Float longitude,
            @Param("latitude") Float latitude,
            @Param("distance") Integer distance);
    }
