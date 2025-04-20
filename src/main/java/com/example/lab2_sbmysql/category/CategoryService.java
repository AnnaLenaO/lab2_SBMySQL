package com.example.lab2_sbmysql.category;

import com.example.lab2_sbmysql.category.entity.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    CategoryRepository categoryRepository;
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDto> allCategories() {
        return categoryRepository.findAll().stream()
                .map(CategoryDto::fromCategory)
                .toList();
    }

    public Optional<CategoryDto> findById(int id) {
        return categoryRepository.findById(id)
                .map(CategoryDto::fromCategory);
    }

    public int addCategory(CategoryDto categoryDto) {
        if(categoryRepository.existsByName(categoryDto.name())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Category name already exists");
        }

        Category category = new Category();
        category.setName(categoryDto.name());
        category.setSymbol(categoryDto.symbol());
        category.setDescription(categoryDto.description());
        return categoryRepository.save(category).getId();
    }
}
