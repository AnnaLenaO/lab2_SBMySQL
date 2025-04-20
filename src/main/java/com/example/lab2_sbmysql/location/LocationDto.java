package com.example.lab2_sbmysql.location;

import com.example.lab2_sbmysql.location.entity.Location;

import java.time.Instant;

public record LocationDto(Integer id, String name, String person_id, String status, String description, Double longitude, Double latitude, Instant created_at, Instant updated_at, Integer category) {
    public static LocationDto fromLocation(Location location) {
        return new LocationDto(location.getId(), location.getName(), location.getPersonId(), location.getStatus(), location.getDescription(), location.getCoordinate().getPosition().getLon(), location.getCoordinate().getPosition().getLat(), location.getCreatedAt(), location.getUpdatedAt(), location.getCategory().getId());
    }
}
