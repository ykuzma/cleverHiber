package by.kuzma.clever.hiber.repository;

import by.kuzma.clever.hiber.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
