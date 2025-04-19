package com.example.lab2_sbmysql.category;

import com.example.lab2_sbmysql.category.entity.Category;
import org.springframework.data.repository.ListCrudRepository;

public interface CategoryRepository extends ListCrudRepository<Category, Integer> {
    boolean existsByName(String name);
}
