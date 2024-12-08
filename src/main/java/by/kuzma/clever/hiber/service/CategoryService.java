package by.kuzma.clever.hiber.service;

import by.kuzma.clever.hiber.dto.CategoryDto;
import by.kuzma.clever.hiber.entity.Car;
import by.kuzma.clever.hiber.entity.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    List<CategoryDto> findAll();
    CategoryDto findById(UUID id);
    CategoryDto addCategory(CategoryDto category);
    void delete(UUID id);
    CategoryDto update(CategoryDto category, UUID id);
}
