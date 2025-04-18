package com.example.lab2_sbmysql.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InitialController {
    @GetMapping("/")
    public String info() {
        return "lab2 Spring Boot MySQL";
    }
}
