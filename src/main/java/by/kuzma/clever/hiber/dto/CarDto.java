package by.kuzma.clever.hiber.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record CarDto(UUID id, @NotBlank String model,
                     @NotBlank String mark, @Positive int yearOfProduction, @Positive double price, CategoryDto category) {

}
