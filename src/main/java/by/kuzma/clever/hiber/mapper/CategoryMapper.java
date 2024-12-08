package by.kuzma.clever.hiber.mapper;

import by.kuzma.clever.hiber.dto.CategoryDto;
import by.kuzma.clever.hiber.entity.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto toCategoryDto(Category category);

    Category toCategory(CategoryDto categoryDto);

    List<Category> toCategories(List<CategoryDto> categoryDtos);

    List<CategoryDto> toCategoryDtos(List<Category> categories);
}
