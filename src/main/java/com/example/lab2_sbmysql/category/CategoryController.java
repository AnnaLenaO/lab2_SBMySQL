package com.example.lab2_sbmysql.category;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class CategoryController {
    CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String info() {
        return "lab2 Spring Boot MySQL";
    }

    @GetMapping("/categories")
    public List<CategoryDto> getAllCategories() {
        return categoryService.allCategories();
    }

    @GetMapping("/categories/{id}")
    public CategoryDto getCategory(@PathVariable int id) {
        return categoryService.findById(id);
    }

    @PostMapping("/categories")
    public ResponseEntity<Void> createCategory(@RequestBody CategoryDto categoryDto) {
        int id = categoryService.addCategory(categoryDto);
        return ResponseEntity.created(URI.create("/categories/" + id)).build();
    }
}
