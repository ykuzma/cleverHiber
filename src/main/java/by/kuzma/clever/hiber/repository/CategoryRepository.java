package by.kuzma.clever.hiber.repository;

import by.kuzma.clever.hiber.entity.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryRepository {

    Category save(Category category);
    List<Category> findAll();
    Category findById(UUID id);
    void deleteById(UUID id);
    Category update(Category category);

}
