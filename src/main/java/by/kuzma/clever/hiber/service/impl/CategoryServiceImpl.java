package by.kuzma.clever.hiber.service.impl;

import by.kuzma.clever.hiber.dto.CategoryDto;
import by.kuzma.clever.hiber.entity.Category;
import by.kuzma.clever.hiber.exception.NotFoundDataException;
import by.kuzma.clever.hiber.mapper.CategoryMapper;
import by.kuzma.clever.hiber.repository.CategoryRepository;
import by.kuzma.clever.hiber.service.CategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    @Override
    public List<CategoryDto> findAll() {

        return mapper.toCategoryDtos(repository.findAll());
    }

    @Override
    public CategoryDto findById(UUID id) {

        return mapper.toCategoryDto(repository.findById(id)
                .orElseThrow(() -> new NotFoundDataException(id, Category.class)));
    }

    @Override
    public CategoryDto addCategory(CategoryDto category) {
        return mapper.toCategoryDto(repository.save(mapper.toCategory(category)));
    }

    @Override
    public void delete(UUID id) {
        if(!repository.existsById(id)) {
            throw new NotFoundDataException(id, Category.class);
        }
        repository.deleteById(id);
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto, UUID id) {
        Category category = mapper.toCategory(categoryDto);
        category.setId(id);
        return mapper.toCategoryDto(repository.save(category));
    }
}
