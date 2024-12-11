package by.kuzma.clever.hiber.controller;

import by.kuzma.clever.hiber.dto.CategoryDto;
import by.kuzma.clever.hiber.service.CategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable @NotNull UUID id) {

        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<CategoryDto> addCategory(@RequestBody @Valid CategoryDto categoryDto) {
        return new ResponseEntity<>(service.addCategory(categoryDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody @ Valid CategoryDto categoryDto, @PathVariable @NotNull UUID id) {
        return new ResponseEntity<>(service.update(categoryDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteCategory(@PathVariable @ NotNull UUID id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


}
