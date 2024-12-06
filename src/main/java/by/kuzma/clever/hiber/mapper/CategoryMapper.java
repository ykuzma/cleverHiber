package by.kuzma.clever.hiber.mapper;

import by.kuzma.clever.hiber.dto.CategoryDto;
import by.kuzma.clever.hiber.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto toCategoryDto(Category category);

    Category toCategory(CategoryDto categoryDto);
}
