package com.example.lab2_sbmysql.category;

import com.example.lab2_sbmysql.category.entity.Category;

public record CategoryDto(
        String name,
        String symbol,
        String description)
{
    public static CategoryDto fromCategory(Category category) {
        return new CategoryDto(
                category.getName(),
                category.getSymbol(),
                category.getDescription());
    }
}
