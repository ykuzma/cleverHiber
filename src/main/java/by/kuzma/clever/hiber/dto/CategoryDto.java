package by.kuzma.clever.hiber.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record CategoryDto(UUID id, @NotBlank @Size(max = 255) String name) {
}
