package com.example.lab2_sbmysql.location;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AdressController {
    AdressService adressService;
    public AdressController(AdressService adressService) {
        this.adressService = adressService;
    }

    @GetMapping("/api/geocode/{latitude}/{longitude}")
    public Optional<JsonNode> getAdress(@PathVariable Float latitude, @PathVariable Float longitude){
        return adressService.getAdressByCoordinates(latitude, longitude);
    }
}
