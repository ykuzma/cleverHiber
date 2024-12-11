package by.kuzma.clever.hiber.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record ClientDto(UUID id, @NotBlank String name, LocalDate dateOfRegistration, List<String> contacts, List<CarDto> cars) {
}
