package com.example.lab2_sbmysql.category;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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
    public Optional<CategoryDto> getCategory(@PathVariable int id) {
        return categoryService.findById(id);
    }
}
