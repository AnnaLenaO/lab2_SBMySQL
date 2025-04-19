package com.example.lab2_sbmysql.location;

import com.example.lab2_sbmysql.location.entity.Location;
import org.springframework.data.repository.ListCrudRepository;

public interface LocationRepository extends ListCrudRepository<Location, Integer> {
}
