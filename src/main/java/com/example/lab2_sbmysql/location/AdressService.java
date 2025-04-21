package com.example.lab2_sbmysql.location;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Optional;

@Service
public class AdressService {
    @Value("${api_key}")
            private String apiKey;

    RestClient restClient;

    public AdressService(RestClient restClient) {
        this.restClient = restClient;
    }

    public Optional<JsonNode> getAdressByCoordinates(Float latitude, Float longitude) {
        return Optional.ofNullable(restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .host("geocode.maps.co")
                        .path("/reverse")
                        .queryParam("lat", latitude)
                        .queryParam("lon", longitude)
                        .queryParam("api_key", apiKey)
                        .build())
                .retrieve()
                .body(JsonNode.class));
    }
}
